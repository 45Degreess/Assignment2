package Assignment_2.src;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class that loads data into AVLTree and runs search queries
 */
public class GenericsKbAVLApp
{
    /**
     * Stores a {@link AVLTree} Object of type {@link Generics}
     */
    private static AVLTree<Generics> avl = new AVLTree<>();

    public static void main(String[] args)
    {
        try
        {
            String relativePath = "lib/GenericsKB.txt";
            String relativePath1 = "Assignment_2/lib/GenericsKB.txt";
            String relativePath2 = "lib/GenericsKB-queries.txt";
            String relativePath3 = "Assignment_2/lib/GenericsKB-queries.txt";
            File fileTemp = new File("");
            String absolutePath = "";
            String absolutePath1 = "";
            if(fileTemp.getAbsolutePath().contains("Assignment_2")) {
                File file = new File(relativePath);
                absolutePath = file.getAbsolutePath();
                file = new File(relativePath2);
                absolutePath1 = file.getAbsolutePath();
            }
            else
            {
                File file = new File(relativePath1);
                absolutePath = file.getAbsolutePath();
                file = new File(relativePath3);
                absolutePath1 = file.getAbsolutePath();
            }
            BufferedReader ff= new BufferedReader(new FileReader(absolutePath));
            String line;
            while((line = ff.readLine()) != null)
            {
                String temp []= line.split("\\t");
                String term = temp[0];
                String sentence = temp[1];
                double confidence = Double.parseDouble(temp[2]);
                avl.insert(new Generics(term, sentence, confidence));

            }
            ff=new BufferedReader(new FileReader(absolutePath1));
            while((line = ff.readLine()) != null)
            {
                AVLTreeNode<Generics> node = avl.search(new Generics(line,"",-1));
                if(node != null)
                    System.out.println(node.getData().toString());
                else
                    System.out.println("The term: "+ line +" ,is not in the knowledge base");
            }
            ff.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
