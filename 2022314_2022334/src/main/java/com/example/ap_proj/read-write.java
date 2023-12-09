public void readFile() throws IOException {
        try{
            Scanner scanner= new Scanner(new File("/Users/namitgupta/Downloads/AP_Project-main/2022314_2022334/src/main/java/com/example/ap_proj/data.txt"));
            if (scanner.hasNextLine()) {
                int score = Integer.parseInt(scanner.nextLine().trim());
                System.out.println("Read method: Score = " + score);
            }
            if (scanner.hasNextLine()) {
                int cherries = Integer.parseInt(scanner.nextLine().trim());
                System.out.println("Read method: Cherries = " + cherries);
            }
            scanner.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    static void writeFile(int score, int cherries) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("/Users/namitgupta/Downloads/AP_Project-main/2022314_2022334/src/main/java/com/example/ap_proj/data.txt"))) {
            // Write score and cherries on separate lines
            writer.println(score);
            writer.println(cherries);
        }
    }
