package model;

import jakarta.persistence.Embeddable;

/**
 * @param position
 *         index in the GPS point list, per link
 */
@Embeddable
public record GPSPointKey(
        //@formatter:off
        LinkKey link,

        //@OrderColumn
        Integer position
        //@formatter:on
) {

}

//@Embeddable
//public class GPSPointKey {
//        public LinkKey link;
//
//        public Integer position;
//}
