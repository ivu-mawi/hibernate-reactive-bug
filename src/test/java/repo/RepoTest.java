package repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.quarkus.test.hibernate.reactive.panache.TransactionalUniAsserter;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import jakarta.inject.Inject;
import model.NetPoint;
import model.NetPointKey;
import model.NetPointType;

@QuarkusTest
public class RepoTest {

    @Inject
    NetPointRepo repo;

    @Test
    @RunOnVertxContext
    void testWithRepo(final TransactionalUniAsserter asserter) {
        asserter.execute(() -> {
            final var np = new NetPoint();
            np.key = new NetPointKey(1, NetPointType.STOP_POINT);
            fillWithBogusValues(np);
            return repo.storeOrUpdateNetPoints(List.of(np));
        });

        asserter.execute(() -> {
            return repo.fetchNetPoints(List.of(new NetPointKey(1, NetPointType.STOP_POINT)))
                    .invoke(list -> assertThat(list).hasSize(1));
        });
    }

    void fillWithBogusValues(NetPoint np) {
        np.gpsLatitude = 1;
        np.gpsLongitude = 1;
        np.operatingDepartmentId = "123";
        np.operatingDepartmentShortName = "123 - 123";
    }

}
