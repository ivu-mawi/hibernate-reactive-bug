package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.hibernate.reactive.panache.TransactionalUniAsserter;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;


@QuarkusTest
public class PersistenceTest {

    @AfterEach
    @RunOnVertxContext
    void deleteAll(final TransactionalUniAsserter asserter) {
        asserter.execute(() -> NetPoint.deleteAll());
        asserter.execute(() -> GPSPoint.deleteAll());
        asserter.execute(() -> Link.deleteAll());
    }

    @Test
    @RunOnVertxContext
    void test(final TransactionalUniAsserter asserter) {

        asserter.execute(() -> {
            final var np = new NetPoint();
            np.key = new NetPointKey(1, NetPointType.STOP_POINT);
            fillWithBogusValues(np);
            return np.persist();
        });

        asserter.execute(() -> NetPoint.<NetPoint>findAll().list().invoke(list -> assertThat(list).hasSize(1)));
    }

    void fillWithBogusValues(NetPoint np) {
        np.gpsLatitude = 1;
        np.gpsLongitude = 1;
        np.operatingDepartmentId = "123";
        np.operatingDepartmentShortName = "123 - 123";
    }

    LinkKey same_setup(final TransactionalUniAsserter asserter) {
        var startKey = new NetPointKey(1, NetPointType.STOP_POINT);
        var endKey = new NetPointKey(2, NetPointType.STOP_POINT);
        var linkKey = new LinkKey(startKey, endKey, "123");
        asserter.execute(() -> {
            final var start = new NetPoint();
            start.key = startKey;
            fillWithBogusValues(start);
            final var end = new NetPoint();
            end.key = endKey;
            fillWithBogusValues(end);
            return start.persist().chain(end::persist);
        });

        asserter.execute(() -> {
            final var link = new Link();
            link.key = linkKey;
            final var gpsPoints = List.of(new GPSPoint(), new GPSPoint(), new GPSPoint());

            for (var i = 0; i < gpsPoints.size(); i++) {
                final var gpsPoint = gpsPoints.get(i);
                gpsPoint.key = new GPSPointKey(linkKey, i);
                gpsPoint.link = link;
                gpsPoint.latitude = 1;
                gpsPoint.longitude = 1;
                gpsPoint.distance = 1;
            }

            link.gpsPoints = gpsPoints;

            return link.persist();
        });

        return linkKey;
    }

    @Test
    @RunOnVertxContext
    void testLinks_fails(final TransactionalUniAsserter asserter) {
        var linkKey = same_setup(asserter);

        asserter.execute(() -> {
            return Link.<Link>findAll().list().invoke(list -> assertThat(list).hasSize(1).first().satisfies(link -> {
                assertThat(link.key).isEqualTo(linkKey);
                assertThat(link.gpsPoints).hasSize(3);
            }));
        });
    }

    @Test
    @RunOnVertxContext
    void testLinks_works(final TransactionalUniAsserter asserter) {
        var linkKey = same_setup(asserter);

        asserter.execute(() -> {
            return Link.<Link>findById(linkKey).invoke(link -> {
                assertThat(link.key).isEqualTo(linkKey);
                assertThat(link.gpsPoints).hasSize(3);
            });
        });
    }

}
