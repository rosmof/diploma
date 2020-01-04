package ro.rosmof;

public abstract class Application {

    /**
     * <p>
     * Static convention values to use and configure beans
     * in the entire application.
     * </p>
     */
    public static abstract class Profiles {
        /**
         * <p>
         * Convention associated to developer mode, thus activating
         * only beans with this profile (e.g. datasource bean)
         * </p>
         */
        public static final String DEVELOPER = "dev";

        /**
         * <p>
         * Convention associated to production mode, thus activating
         * only beans with this profile (e.g. datasource bean)
         * </p>
         */
        public static final String PRODUCTION = "prod";
    }
}
