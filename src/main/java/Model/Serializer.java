package Model;

public interface Serializer {

    /**
     * Takes all the instance variables 
     * @return A comma-delimited String containing the variables
     */
    String serialize();

    /**
     * Sets all the instance variables according to the data from the specified file
     * @param file - The file to extract data from
     */
    void deSerialize(String file);

}