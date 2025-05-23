package model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public record NetPointKey(
        //@formatter:off
        Integer id,

        @Enumerated(EnumType.ORDINAL)
        NetPointType type
        //@formatter:on
) {

}

//@Embeddable
//public class NetPointKey {
//
//    public Integer id;
//
//    @Enumerated(EnumType.ORDINAL)
//    public NetPointType type;
//
//    public NetPointKey(final int i, final NetPointType netPointType) {
//        this.id = i;
//        this.type = netPointType;
//    }
//
//    public NetPointKey() {
//
//    }
//}
