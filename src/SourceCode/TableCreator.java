package SourceCode;

/*
 * @author seanrHagen
 * Version April 3 2019
 * This class was made for two projects and really fails to be useable for other projects
 */
public final class TableCreator {

    private String line;
    private int[] biggest;
    private final long[][] table;
    private final String title;
    private final String[] Collumn;
    private final int padding = 4;

    /**
     * Creates an asci table when given an array of longs
     * @param title the title of the asci table
     * @param table a two dimensional array of type long to build an asci table from
     * @param Collumn the collumn headers for each collumn in the table
     */
    public TableCreator(String title, long[][] table, String... Collumn) {
        this.title = title;
        this.table = table;
        this.Collumn = Collumn;
        lineCreator();
        buildHeader();
        buildLines(table);

    }

    /**
     * this method creates each row of the table when given a two dimensional
     * array. It checks for special cases such as -1, -2 and -3 that represent
     * timeout, N/A and out of mem respectively
     *
     * @param table a two dimensional table of long values
     */
    public void buildLines(String[][] table) {

        for (int x = 0; x < table.length; x++) {
            String fstring = "|  %" + (biggest[0] - padding) + "d  ";
            switch ((int) table[x][0]) {
                case -1:
                    fstring = "|  %" + (biggest[0] - padding) + "s  ";
                    System.out.printf(fstring, "TimeOut  ");
                    break;
                case -2:
                    fstring = "|  %" + (biggest[0] - padding) + "s  ";
                    System.out.printf(fstring, "N/A");
                    break;
                case -3:
                    fstring = "|  %" + (biggest[0] - padding) + "s  ";
                    System.out.printf(fstring, "Out of Mem");
                    break;
                default:
                    System.out.printf(fstring, table[x][0]);
                    break;
            }

            for (int y = 1; y < table[x].length - 1; y++) {
                fstring = "|%" + (biggest[y] - 2) + "d  ";
                switch ((int) table[x][y]) {
                    case -1:
                        fstring = "|  %" + (biggest[y] - 2) + "s";
                        System.out.printf(fstring, "TimeOut  ");
                        break;
                    case -2:
                        fstring = "|  %" + (biggest[y] - 2) + "s";
                        System.out.printf(fstring, "N/A  ");
                        break;
                    case -3:
                        fstring = "|  %" + (biggest[y] - 2) + "s";
                        System.out.printf(fstring, "Out of Mem  ");
                        break;
                    default:
                        System.out.printf(fstring, table[x][y]);
                        break;
                }

            }
            fstring = "|%" + (biggest[biggest.length - 1] - 2) + "d  |\n";
            switch ((int) table[x][table[x].length - 1]) {
                case -1:
                    fstring = "|  %" + (biggest[biggest.length - 1] - 2) + "s|\n";
                    System.out.printf(fstring, "TimeOut  ");
                    break;
                case -2:
                    fstring = "|  %" + (biggest[biggest.length - 1] - 2) + "s|\n";
                    System.out.printf(fstring, "N/A  ");
                    break;
                case -3:
                    fstring = "|  %" + (biggest[biggest.length - 1] - 2) + "s|\n";
                    System.out.printf(fstring, "Out of Mem  ");
                    break;
                default:
                    System.out.printf(fstring, table[x][table[x].length - 1]);
                    break;
            }
            System.out.println(line);
        }
    }

    /**
     * creates the header block for the table, including title and collumn
     * titles uses print format in multiple lines
     */
    public void buildHeader() {
        //prints out the title block above the collumn headers
        int left = (line.length() / 2 + title.length() / 2);
        int right = line.length() - left - 2;
        String fmate = "|%" + left + "s%" + right + "s|\n";
        System.out.println(line);
        System.out.printf(fmate, title, "");
        System.out.println(line);
        for (int x = 0; x < Collumn.length; x++) {
            left = (biggest[x] / 2 + Collumn[x].length() / 2);
            right = biggest[x] - left;
            if (x == Collumn.length) {
                fmate = "|%" + left + "s%" + right + "s|\n";
            } else {
                fmate = "|%" + left + "s%" + right + "s";
            }
            //if it is the last entry, add a bar and a new line character
            System.out.printf(fmate, Collumn[x], " ");
        }
        System.out.println("|\n" + line);
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
     * goes through the MapEntryInt and TestNumber arrays to find the longest
     * value (String Length) in them with padding
     *
     * @return an array of int where the first entry is the longest testCount
     * item, The second is the longest total Collisions count and the third is
     * the longest max collisions count
     */
    private void findTheBiggest() {
        //for each collumn, find the largest entry
        int[] lengths = new int[Collumn.length];
        //sets the respective collumns to the length of thier headers
        //then looks for longer entries in the collumns
        for (int x = 0; x < lengths.length; x++) {
            lengths[x] = Collumn[x].length() + padding;
        }

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
