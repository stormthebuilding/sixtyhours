package Model;

public interface Serializer {

    /**
     * Takes instance variables 
     * @return A comma-delimited String containing the variables
     */
    String serialize();

    /**
     * Sets instance variables according to the data from the specified file
     * @param  - The file to extract data from
     */
    void deserialize(String data);

}