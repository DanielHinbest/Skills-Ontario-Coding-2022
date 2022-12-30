package ca.danielhinbest;

import java.util.Date;

/**
 * Class of an animal
 * Daniel Hinbest
 * April 28, 2022
 */
public class Animal {
    /**
     * The id number
     */
    private int id;
    /**
     * Animal species
     */
    private String species;
    /**
     * Animal name
     */
    private String name;
    /**
     * Animal gender
     */
    private String gender;
    /**
     * Spay status
     */
    private boolean isSpayed;
    /**
     * Animal breed
     */
    private String breed;
    /**
     * Animal colour
     */
    private String colour;
    /**
     * Animal's birthday
     */
    private Date birthday;
    /**
     * Vaccination status
     */
    private String vaccineStatus;
    /**
     * Identification method
     */
    private String identification;
    /**
     * Cost of adoption
     */
    private int adoptionFee;
    /**
     * Date of Adoption
     */
    private Date adoptionDate;

    /**
     * Archive status
     */
    private boolean isArchived;

    /**
     * Create a new animal with default attributes
     */
    public Animal() {
        setId(0);
        setSpecies(null);
        setName(null);
        setGender(null);
        setBreed(null);
        setColour(null);
        setSpayed(false);
        setBirthday(new Date());
        setVaccineStatus(null);
        setIdentification(null);
        setAdoptionFee(0);
        setArchived(false);
        setAdoptionDate(null);
    }

    /**
     * Create a new animal with specific attributes
     * @param id ID number
     * @param species Species
     * @param name Name
     * @param gender Gender
     * @param isSpayed Spay status
     * @param breed Breed
     * @param colour Colour
     * @param birthday Birthday
     * @param vaccineStatus Vaccination status
     * @param identification Identification method
     * @param adoptionFee Adoption fee
     */
    public Animal(int id, String species, String name, String gender, boolean isSpayed, String breed, String colour,
                  Date birthday, String vaccineStatus, String identification, int adoptionFee) {
        this.id = id;
        this.species = species;
        this.name = name;
        this.gender = gender;
        this.isSpayed = isSpayed;
        this.breed = breed;
        this.colour = colour;
        this.birthday = birthday;
        this.vaccineStatus = vaccineStatus;
        this.identification = identification;
        this.adoptionFee = adoptionFee;
        this.isArchived = false;
        this.adoptionDate = null;
    }

    /**
     * Get the animal ID
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the animal ID
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the species
     * @return species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Set the species
     * @param species species
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * Get the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the gender
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the gender
     * @param gender gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the spay status
     * @return spay status
     */
    public boolean isSpayed() {
        return isSpayed;
    }

    /**
     * Set the spay status
     * @param spayed spay status
     */
    public void setSpayed(boolean spayed) {
        isSpayed = spayed;
    }

    /**
     * Get the breed
     * @return breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Set the breed
     * @param breed breed
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * Get the colour
     * @return colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Set the colour
     * @param colour colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Get the birthday
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set the birthday
     * @param birthday birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Get the vaccine status
     * @return vaccine status
     */
    public String getVaccineStatus() {
        return vaccineStatus;
    }

    /**
     * Set the vaccine status
     * @param vaccineStatus vaccine status
     */
    public void setVaccineStatus(String vaccineStatus) {
        this.vaccineStatus = vaccineStatus;
    }

    /**
     * Get the identification method
     * @return identification method
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Set the identification method
     * @param identification identification method
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Set the adoption fee
     * @return adoption fee
     */
    public int getAdoptionFee() {
        return adoptionFee;
    }

    /**
     * Set the adoption fee
     * @param adoptionFee adoption fee
     */
    public void setAdoptionFee(int adoptionFee) {
        this.adoptionFee = adoptionFee;
    }

    /**
     * Get the adoption date
     * @return adoption date
     */
    public Date getAdoptionDate() {
        return adoptionDate;
    }

    /**
     * Set the adoption date
     * @param adoptionDate the adoption date
     */
    public void setAdoptionDate(Date adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    /**
     * Return archive status
     * @return archive status
     */
    public boolean isArchived() {
        return isArchived;
    }

    /**
     * Set the archive status
     * @param archived archived status
     */
    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    /**
     * Return the object as a one line string to print to file
     * @param animal an animal object
     * @return one line string
     */
    public String oneLineOutput(Animal animal) {
        return id + "," + name + "," + species + "," + gender + "," + isSpayed + "," + breed + "," + colour +
                "," + birthday + "," + vaccineStatus + "," + identification + "," + adoptionFee + "," + adoptionDate + "," + isArchived;
    }

    /**
     * Output the animal to a string
     * @return animal class as a string
     */
    @Override
    public String toString() {
        return "Animal: " + name +
                "\n\tID: " + id +
                "\n\tSpecies: " + species +
                "\n\tGender: " + gender +
                "\n\tSpayed? " + isSpayed +
                "\n\tBreed: " + breed +
                "\n\tColour: " + colour +
                "\n\tBirthday: " + birthday +
                "\n\tVaccine Status: " + vaccineStatus +
                "\n\tIdentification: " + identification +
                "\n\tAdoption Fee: " + adoptionFee;
    }
}
