package SourceCode;

/*
 * @author seanrHagen
 * Version April 3 2019
 * This class was made for two projects and really fails to be useable for other projects
 */
public final class TableCreator {

    private String line;
    private int[] biggest;
    private final String[][] table;
    private final int padding = 4;

    /**
     * creates and outputs an asci table from a two dimensional string array
     * @param title
     * @param table 
     */
    public TableCreator(String[][] table) {
        this.table = table;
        lineCreator();
        buildLines(table);

    }

  /**
   * Takes the two dimensional table and converts it to an asci table.
   * replaces null values with a space
   * @param table a two dimensional string table
   */
    public void buildLines(String[][] table) {
        System.out.println(line);
        for (int x = 0; x < table.length; x++) {
            for (int y = 0; y < table[x].length; y++) {
                //typical format string
                String fstring = "|%" + (biggest[y] - 2) + "s  ";
                if (y == table[x].length - 1) {
                    //if the last cell in the row, add another | to end the row
                    fstring = "|%" + (biggest[y] - 2) + "s  |\n";
                }
                if (x == 0 && y == 0) {
                    //output an empty cell if null
                    System.out.printf(fstring, "");
                } else {
                    System.out.printf(fstring, table[y][x]);
                }

            }
            System.out.println(line);

        }

    }

    /**
     * goes through the lengths given in the findTheBiggest method and returns a
     * line corresponding to the length needed
     */
    public void lineCreator() {
        findTheBiggest();
        int[] lengths = biggest;
        //total will be the number of characters needed for a seperator line in this table
        int total = 0;
        for (int x : lengths) {
            total += x;
        }
        StringBuilder line = new StringBuilder(total + table.length + 1); //table + 1 being the number of "+" separators needed in the table
        line.append("+");
        for (int x : lengths) {
            for (int y = 0; y < x; y++) {
                line.append("-");
            }
            line.append("+");
        }
        line.trimToSize();
        this.line = line.toString();
    }

   /**
    * finds the biggest entrys in terms of length and adds them to longest collumn entry.
    */
    private void findTheBiggest() {
        //for each collumn, find the largest entry
        int[] lengths = new int[table.length];
        for (int x = 0; x < table.length; x++) {
            for (int y = 0; y < table[x].length; y++) {
                String temp = table[x][y] + "";
                //checks to see if this table entry is bigger than the collumn header
                if (lengths[y] < temp.length() + padding) {
                    //sets longest collumn length to this table entry length
                    lengths[y] = temp.length() + padding;
                }
            }

        }
        biggest = lengths;

    }
}
