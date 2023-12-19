import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
public class SearchLogic {

    public SearchLogic(){

    }

    public void executeProgram(){
        boolean continueSearch = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("File to read:");
        String fileInput = scanner.next();
        System.out.println("\nCommands:\nlist - lists the recipes\nstop - stops the program\nfind name - searches recipes by name");
        System.out.println("find cooking time - searches recipes by cooking time\nfind ingredient - searches recipes by ingredient");
        scanner.nextLine();
        while (continueSearch){
            System.out.println("\nEnter command:");
            String userInputCommand = scanner.nextLine();
            if (userInputCommand.equals("list")) {
                listRecipes(fileInput);
            } else if (userInputCommand.equals("find name")) {
                System.out.println("Searched word: ");
                String wordToFind = scanner.nextLine();
                findRecipeByName(fileInput, wordToFind);
            } else if (userInputCommand.equals("find cooking time")) {
                System.out.println("Max cooking time:");
                int maxCookingTime = scanner.nextInt();
                findRecipeByCookTime(fileInput, maxCookingTime);
                scanner.nextLine();
            } else if (userInputCommand.equals("find ingredient")) {
                System.out.println("Ingredient:");
                String ingredientToFind = scanner.nextLine();
                findRecipeByIngredient(fileInput, ingredientToFind);
//                System.out.println("next");
                //scanner.nextLine();
            }
            if (userInputCommand.equals("stop")) {
                continueSearch = false;
            }
        }
    }

    public void findRecipeByIngredient(String inputFile, String findIngredient) {
        System.out.println("Recipes:");
        ArrayList<ArrayList<String>> recipes = new ArrayList<ArrayList<String>>();
        try (Scanner scanner = new Scanner(Paths.get(inputFile))) {
            ArrayList<String> recipe = new ArrayList<String>();
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String row = "hello";
                if (row.equals("") || lineNumber == 0) {
                    recipe = new ArrayList<String>();
                }
                row = scanner.nextLine();
                lineNumber++;
                recipe.add(row);
                if (row.equals("")) {
                    lineNumber = 0;
                    recipes.add(recipe);
                }
                if (!(scanner.hasNextLine())) {
                    recipes.add(recipe);
                }

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (ArrayList<String> allRecipes : recipes) {
            for (String ingredients : allRecipes) {
                if (ingredients.equals(findIngredient)){
                    System.out.println(allRecipes.get(0) + ", cooking time: " + allRecipes.get(1));
                }
            }
        }
    }

    public void findRecipeByCookTime(String inputFile, int maxTime) {
        System.out.println("Recipes:");
        ArrayList<ArrayList<String>> recipes = new ArrayList<ArrayList<String>>();
        try (Scanner scanner = new Scanner(Paths.get(inputFile))) {
            ArrayList<String> recipe = new ArrayList<String>();
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String row = "hello";
                if (row.equals("") || lineNumber == 0) {
                    recipe = new ArrayList<String>();
                }
                row = scanner.nextLine();
                lineNumber++;
                recipe.add(row);
                if (row.equals("")) {
                    lineNumber = 0;
                    recipes.add(recipe);
                }
                if (!(scanner.hasNextLine())) {
                    recipes.add(recipe);
                }

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        int i = 0;
        for (ArrayList<String> allRecipes : recipes) {
            for (String ingredients : allRecipes) {
                if (i == 1){
                    if (Integer.parseInt(ingredients) <= maxTime){
                        System.out.println(allRecipes.get(0) + ", cooking time: " + allRecipes.get(1));
                    } else {
                        i = 0;
                        break;
                    }
                }
                i++;
                if (ingredients.equals("")) {
                    i = 0;
                }

            }
        }
    }

    public void listRecipes(String userInputFile) {
        System.out.println("Recipes:");
        try (Scanner scanner = new Scanner(Paths.get(userInputFile))) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                if (lineNumber == 0){
                    System.out.print(row + ", ");
                    lineNumber++;
                } else if (lineNumber == 1) {
                    System.out.println("cooking time: " + row);
                    lineNumber++;
                } else if (row.isEmpty()) {
                    lineNumber = 0;
                } else {
                    continue;
                }

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void findRecipeByName(String userInputFile, String wordToFind){
        System.out.println("Recipes:");
        try (Scanner scanner = new Scanner (Paths.get(userInputFile))) {
            int lineNumber = 0;
            String row = "";
            while (scanner.hasNextLine()) {
                if (row.equals("")) {
                    lineNumber = 0;
                }
                row = scanner.nextLine();
                if (!(row.contains(wordToFind))) {
                    lineNumber++;
                }

                if (row.contains(wordToFind) && lineNumber == 0) {
                    while (!(row.isEmpty())) {
                        if (lineNumber == 0) {
                            System.out.print(row + ", ");
                            lineNumber++;
                            row = scanner.nextLine();
                        } else if (lineNumber == 1) {
                            System.out.println("cooking time: " + row);
                            lineNumber++;
                            row = scanner.nextLine();
                        } else if (!scanner.hasNextLine()) {
                            break;
                        } else if (lineNumber > 1) {
                            String nextLine = scanner.nextLine();
                            if (nextLine.isBlank()) {
                                row = nextLine;
                                break;
                            } else {
                                row = nextLine;
                            }
                        }
                    }
                    lineNumber = 0;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}