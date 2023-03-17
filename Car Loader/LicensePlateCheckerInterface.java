public interface LicensePlateCheckerInterface {

    /**
     * Ches if the given license Plate is a valid Licence Plate.
     * This means that it is a string of 6 characters, the first 3 of which are
     * 3 numbers of any combination then 3 letters of any combination
     * @param String License Plate
     * @return true if the license Plate is valid, Otherwise False
     */
    public boolean validate(String LicensePlate);
}

