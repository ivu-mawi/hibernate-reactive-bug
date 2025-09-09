package repo;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import model.NetPoint;
import model.NetPointKey;

@ApplicationScoped
public class NetPointRepo implements PanacheRepositoryBase<NetPoint, NetPointKey> {

    public Uni<Void> storeOrUpdateNetPoints(final Collection<NetPoint> netPoints) {
        return Panache.getSession().chain(session -> {
            return session.mergeAll(netPoints.toArray());
        });
    }

    public Uni<List<NetPoint>> fetchNetPoints(final Collection<NetPointKey> entityKeys) {
        return list("id in ?1", entityKeys.toArray()).map(result -> result.stream().filter(Objects::nonNull).toList());
    }
}
