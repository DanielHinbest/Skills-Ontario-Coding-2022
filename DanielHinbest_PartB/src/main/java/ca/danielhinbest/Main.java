package ca.danielhinbest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The main runtime class
 * Daniel Hinbest
 * April 28, 2022
 */
public class Main {
    /**
     * A list of animals
     */
    public static List<Animal> animals = new ArrayList();
    /**
     * The path to the data file
     */
    public static Path path = Paths.get("Animals.csv");
    /**
     * A new file for the data
     */
    public static File file = new File("Animals.csv");
    /**
     * The ID for user input
     */
    public static int id = 0;

    /**
     * Main runtime function
     * @param args any needed arguments
     */
    public static void main(String args[]) throws IOException {
        // Scanner to take user input
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;

        do {
            id = 0;
            String search = "";
            displayMenu();
            userInput = scanner.nextInt();
            boolean isValid = false;
            // Conditions for user input
            switch(userInput) {
                // If the user input is 1, add a new animal
                case 1:
                    addNewAnimal();
                    break;
                // If the user input is 2, remove an animal
                case 2:
                    System.out.println("Please enter the ID of the animal you want to remove: ");
                    id = scanner.nextInt();
                    removeAnimal(id);
                    break;
                // If the user input is 3, search for an animal
                case 3:
                    System.out.println("Please enter the ID of the animal you want to remove: ");
                    System.out.print("\nSearch by Name or Species:");
                    System.out.print("\nEnter a name: ");
                    search = scanner.next();
                    searchForAnimal(search);
                    break;
                // If the user input is 4, display animals
                case 4:
                    displayAnimals();
                    break;
                // If the user input is 5, display the oldest animals
                case 5:
                    displayOldestAnimals();
                    break;
                // If the user input is 6, set the adoption date
                case 6:
                    adoptAnimal();
                    break;
                // If the user input is 7, archive animals adopted older than three months
                case 7:
                    archiveAnimals();
                    break;
                // If the user input is 7, search archives to show animals
                case 8:
                    searchArchives();
                    break;
                // If the user input is 9, display instructions
                case 9:
                    displayInstructions();
                    break;
            }

        } while(userInput != 0);
        scanner.close();
    }

    /**
     * Display the menu options to the user
     */
    public static void displayMenu() {
        // Output of menu items
        System.out.print("Animal Rescue Management" +
                "\nPlease select an option:" +
                "\n\t1. Add new animal" +
                "\n\t2. Remove animal" +
                "\n\t3. Search for animal" +
                "\n\t4. Display animals" +
                "\n\t5. Display oldest animals" +
                "\n\t6. Adopt an animal" +
                "\n\t7. Archive adopted animals" +
                "\n\t8. Search archived animals" +
                "\n\t9. Instructions" +
                "\n\t0. Exit" +
                "\nWhat would you like to do: ");
    }

    /**
     * Adds a new animal to the list and file
     * @throws IOException file access exception
     */
    public static void addNewAnimal() throws IOException {
        // User input
        Scanner scanner = new Scanner(System.in);
        // New animal class
        Animal animal = new Animal();
        System.out.println("Add new Animal");
        boolean isValid = false;

        // Set the ID
        System.out.println("Please enter the information below:");
        id++;
        animal.setId(id);
        // Set the species
        System.out.print("Species: ");
        animal.setSpecies(scanner.nextLine());

        // Set the name
        System.out.print("Name: ");
        animal.setName(scanner.nextLine());

        // Set the gender
        do {
            // Check for the gender input
            System.out.print("Gender (F/M): ");
            String gender = scanner.nextLine();
            // Assign F for female and M for male
            if (gender.charAt(0) == "F".charAt(0) || gender.charAt(0) == "f".charAt(0)) {
                animal.setGender("F");
                isValid = true;
            }
            else if (gender.charAt(0) == "M".charAt(0) || gender.charAt(0) == "m".charAt(0)) {
                animal.setGender("M");
                isValid = true;
            } else {
                System.out.println("Please enter F or M for gender");
            }
        } while(!isValid);

        // Set the spay status
        isValid = false;
        do {
            System.out.print("Spayed (Y/N)? ");
            String spayed = scanner.nextLine();
            // Set true for 'Y' and false for 'N'
            if (spayed.charAt(0) == "Y".charAt(0) || spayed.charAt(0) == "y".charAt(0)) {
                animal.setSpayed(true);
                isValid = true;
            } else {
                animal.setSpayed(false);
                isValid = true;
            }
        } while(!isValid);

        // Set the breed
        System.out.print("Breed: ");
        animal.setBreed(scanner.nextLine());

        // Set the colour
        System.out.print("Colour: ");
        animal.setColour(scanner.nextLine());

        // Set the birthday
        System.out.print("Birthday" +
                "\n\tYear: ");
        int year = scanner.nextInt();
        System.out.print("\tMonth: ");
        int month = scanner.nextInt();
        System.out.print("\tDay: ");
        int day = scanner.nextInt();
        Date birthday = new Date(year, month, day);
        animal.setBirthday(birthday);

        // Set vaccine status
        isValid = false;
        int userInput = 0;
        do {
            System.out.print("Vaccine Status (Select an option):" +
                    "\n\t1. Up to date" +
                    "\n\t2. Late" +
                    "\n\t3. Unknown" +
                    "\nSelect an option: ");
            userInput = scanner.nextInt();
            if (userInput == 1) {
                animal.setVaccineStatus("Up to date");
                isValid = true;
            }
            else if (userInput == 2) {
                animal.setVaccineStatus("Late");
                isValid = true;
            }
            else if (userInput == 3) {
                animal.setVaccineStatus("Unknown");
                isValid = true;
            } else {
                System.out.println("Please select an option");
            }
        } while (!isValid);

        // Set identification
        System.out.println("Identification - Barcode or microchip number: ");
        animal.setIdentification(scanner.nextLine());

        // Set the year
        // TODO: Make the date dynamic
        Date date = new Date();
        int yearDifference = (date.getYear() + 1900) - (animal.getBirthday().getYear());
        if (yearDifference >= 10) {
            animal.setAdoptionFee(100);
        }
        else if (yearDifference < 1) {
            animal.setAdoptionFee(300);
        } else {
            animal.setAdoptionFee(200);
        }
        System.out.println("Adoption Fee: " + animal.getAdoptionFee());

        // Add to list
        animals.add(animal);

        // Write to Animals.csv
        try {
            // Used to write to the file
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("ID,Species,Name,Gender,Spayed,Breed,Colour,Birthday,Vaccine Status,Identification,Adoption Fee,Adoption Date,Archived");
            writer.newLine();
            for (int i = 0; i < animals.size(); i++) {
                writer.write(animals.get(i).oneLineOutput(animals.get(i)));
                writer.newLine();
                System.out.println("Animal added successfully");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Remove an animal from the list
     * @param id id
     * @throws IOException file access exception
     */
    public static void removeAnimal(int id) throws IOException {
        // Populate the list
        populateAnimalList();
        System.out.println("Remove Animal");

        try {
            // Create a path to the file and add the headers
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("ID,Species,Name,Gender,Spayed,Breed,Colour,Birthday,Vaccine Status,Identification,Adoption Fee");
            for (int i = 0; i < animals.size(); i++) {
                //Remove the animal if the ID matches
                if (animals.get(i).getId() == id) {
                    animals.remove(i);
                }
            }
            //Overwrite the file
            for (int i = 0; i < animals.size(); i++) {
                writer.write(animals.get(i).oneLineOutput(animals.get(i)));
                writer.newLine();
                System.out.println("Animal removed");
            }
            //Close the BufferedWriter
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Search function based on name or species
     * @param searchOption the search value
     * @throws IOException file access error
     */
    public static void searchForAnimal(String searchOption) throws IOException {
        // File Access
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        // Populate Animal list
        populateAnimalList();
        try {
            String line = bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null) {
                String[] items = line.split(",");
                try {
                    if (items[12].contains("FALSE")) {
                        // If the file row contains the search value, then output the information
                        if (items[2].contains(searchOption) || items[1].contains(searchOption)) {
                            Animal animal = new Animal();
                            animal.setId(Integer.parseInt(items[0]));
                            animal.setSpecies(items[1]);
                            animal.setName(items[2]);
                            animal.setGender(items[3]);
                            animal.setSpayed(Boolean.parseBoolean(items[4]));
                            animal.setBreed(items[5]);
                            animal.setColour(items[6]);
                            animal.setBirthday(new Date(items[7]));
                            animal.setVaccineStatus(items[8]);
                            animal.setIdentification(items[9]);
                            animal.setAdoptionFee(Integer.parseInt(items[10]));
                            System.out.println(animal);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException|NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            bufferedReader.close();
        }
    }

    /**
     * Show the animals sorted by species
     * @throws IOException file access exception
     */
    public static void displayAnimals() throws IOException {
        // Populate the list if it is empty
        if (animals.size() == 0) {
            populateAnimalList();
        }

        // Sort the list by species
        animals.sort(Comparator.comparing(Animal::getSpecies));

        // Output the sorted list
        System.out.println("ID,Species,Name,Gender,Spayed,Breed,Colour,Birthday,Vaccine Status,Identification,Adoption Fee");
        for (int i = 0; i < animals.size(); i++) {
            if (!animals.get(i).isArchived()) {
                System.out.println(animals.get(i).oneLineOutput(animals.get(i)));
            }
        }
    }

    /**
     * Display the oldest three animals
     * @throws IOException File access exception
     */
    public static void displayOldestAnimals() throws IOException {
        // Create a new buffered reader to read the file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Scanner scanner = new Scanner(System.in);
        // Ask for species
        System.out.print("Name the species you want to show: ");
        String species = scanner.next();

        // Check if the list has content
        if (animals.size() == 0) {
            try {
                String line = bufferedReader.readLine();
                while ((line = bufferedReader.readLine()) != null) {
                    String[] items = line.split(",");
                    try {
                        animals.clear();
                        // Populate the list if it is empty
                        if (animals.size() == 0) {
                            if (items[12].contains("FALSE")) {
                                // Add the item to the list if the species matches
                                if (items[2].contains(species)) {
                                    Animal animal = new Animal();
                                    animal.setId(Integer.parseInt(items[0]));
                                    animal.setSpecies(items[1]);
                                    animal.setName(items[2]);
                                    animal.setGender(items[3]);
                                    animal.setSpayed(Boolean.parseBoolean(items[4]));
                                    animal.setBreed(items[5]);
                                    animal.setColour(items[6]);
                                    animal.setBirthday(new Date(items[7]));
                                    animal.setVaccineStatus(items[8]);
                                    animal.setIdentification(items[9]);
                                    animal.setAdoptionFee(Integer.parseInt(items[10]));
                                    animals.add(animal);
                                }
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                bufferedReader.close();
            }
        }

        // Sort by birthday
        animals.sort(Comparator.comparing(Animal::getBirthday));

        // Output the sorted list
        System.out.println("ID,Species,Name,Gender,Spayed,Breed,Colour,Birthday,Vaccine Status,Identification,Adoption Fee");
        // Loop the size of the list if it's less than three, otherwise loop 3 times
        if (animals.size() <= 3) {
            for (int i = 0; i < animals.size(); i++) {
                System.out.println(animals.get(i).oneLineOutput(animals.get(i)));
            }
        } else {
            for (int i = 0; i < 3; i++) {
                System.out.println(animals.get(i).oneLineOutput(animals.get(i)));
            }
        }
    }

    /**
     * Adopt animal function
     * @throws IOException File access exception
     */
    private static void adoptAnimal() throws IOException {
        // Populate the list and ask for input
        populateAnimalList();
        System.out.println("Adopt Animal");
        System.out.print("Enter the ID of the animal: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        // Set the adoption date
        for (int i = 0; i < animals.size(); i++) {
            animals.get(i).setAdoptionDate(new Date());
        }

        // Write to Animals.csv
        try {
            // Used to write to the file
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("ID,Species,Name,Gender,Spayed,Breed,Colour,Birthday,Vaccine Status,Identification,Adoption Fee,Adoption Date,Archived");
            writer.newLine();
            for (int i = 0; i < animals.size(); i++) {
                writer.write(animals.get(i).oneLineOutput(animals.get(i)));
                writer.newLine();
                System.out.println("Animal updated successfully");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Archives animals adopted at least three months prior
     * @throws IOException File access exception
     */
    private static void archiveAnimals() throws IOException {
        // Populate the animal list
        populateAnimalList();
        // Loop through the list
        for (int i = 0; i < animals.size(); i++) {
            // Get the difference of the today and the adoption month
            Date today = new Date();
            Date adoptionDate = new Date(animals.get(i).getAdoptionDate().getDate());
            int month = today.getMonth();
            int adoptionMonth = adoptionDate.getMonth();
            int monthDifference = adoptionMonth - month;
            //If the difference is greater than three years then archive it.
            if (monthDifference >= 3) {
                animals.get(i).setArchived(true);
            }
        }
        // Write to Animals.csv
        try {
            // Used to write to the file
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("ID,Species,Name,Gender,Spayed,Breed,Colour,Birthday,Vaccine Status,Identification,Adoption Fee,Adoption Date,Archived");
            writer.newLine();
            for (int i = 0; i < animals.size(); i++) {
                writer.write(animals.get(i).oneLineOutput(animals.get(i)));
                writer.newLine();
                System.out.println("Updated archives successfully");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Search all archives
     * @throws IOException File access exception
     */
    private static void searchArchives() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search archives");

        // File Access
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        // Populate Animal list
        populateAnimalList();
        try {
            String line = bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null) {
                String[] items = line.split(",");
                try {
                    if (items[12].contains("TRUE")) {
                        // If the file row contains the search value, then output the information
                        Animal animal = new Animal();
                        animal.setId(Integer.parseInt(items[0]));
                        animal.setSpecies(items[1]);
                        animal.setName(items[2]);
                        animal.setGender(items[3]);
                        animal.setSpayed(Boolean.parseBoolean(items[4]));
                        animal.setBreed(items[5]);
                        animal.setColour(items[6]);
                        animal.setBirthday(new Date(items[7]));
                        animal.setVaccineStatus(items[8]);
                        animal.setIdentification(items[9]);
                        animal.setAdoptionFee(Integer.parseInt(items[10]));
                        System.out.println(animal);
                    }
                } catch (ArrayIndexOutOfBoundsException|NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            bufferedReader.close();
        }
    }

    /**
     * Displays the instructions to the user
     */
    public static void displayInstructions() {
        //Output the user guide
        System.out.println("Instructions");
        System.out.print("To select an item, enter the number corresponding to the list item you want to do." +
                "\nAdd new Animal will create a new animal in the database." +
                "\nRemove animal will ask for the ID of the animal you want to remove, then removes it" +
                "\nSearch for animal will search for the animal by name or species" +
                "\nDisplay oldest animals with display the three oldest animals of the species you provided." +
                "\nTo exit, enter 0.");
    }

    /**
     * Populate the list of animals
     * @throws IOException File access exception
     */
    public static void populateAnimalList() throws IOException {
        // Read the file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        // Check if the list has any content
        if (animals.size() == 0) {
            try {
                // Loop through the file and add the animals to the list
                String line = bufferedReader.readLine();
                while ((line = bufferedReader.readLine()) != null) {
                    String[] items = line.split(",");
                    try {
                        Animal animal = new Animal();
                        animal.setId(Integer.parseInt(items[0]));
                        animal.setSpecies(items[1]);
                        animal.setName(items[2]);
                        animal.setGender(items[3]);
                        animal.setSpayed(Boolean.parseBoolean(items[4]));
                        animal.setBreed(items[5]);
                        animal.setColour(items[6]);
                        animal.setBirthday(new Date(items[7]));
                        animal.setVaccineStatus(items[8]);
                        animal.setIdentification(items[9]);
                        animal.setAdoptionFee(Integer.parseInt(items[10]));
                        Date adoptionDate = new Date();
                        animal.setAdoptionDate(adoptionDate);
                        animals.add(animal);

                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                //Close the buffered reader
                bufferedReader.close();
            }
        }
    }
}
