package model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public record LinkKey(
        //@formatter:off
        @Embedded
        NetPointKey start,

        @Embedded
        NetPointKey end,

        String operatingDepartmentId
        //@formatter:on
) {

}

//@Embeddable
//public class LinkKey {
//
//    public NetPointKey start;
//
//    public NetPointKey end;
//
//    public String operatingDepartmentId;
//
//    public LinkKey(final NetPointKey startKey, final NetPointKey endKey, final String number) {
//        this.start = startKey;
//        this.end = endKey;
//        this.operatingDepartmentId = number;
//    }
//
//    public LinkKey() {
//
//    }
//}