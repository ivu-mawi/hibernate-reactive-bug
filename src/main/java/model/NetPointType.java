package model;

/**
 * Types of net point.
 * <p>
 * The ordinal values of this enum are used in the database.
 * Do not remove or change the order of existing values.
 * If you do that, you must create a database migration.
 */
public enum NetPointType {
    @Deprecated UNSPECIFIED,
    STOP_POINT,
    DEPOT_POINT,
    BEACON,
    SECTION_POINT
}
