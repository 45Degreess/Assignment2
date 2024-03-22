package Assignment_2.src;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main class that loads data into AVLTree and runs search queries
 */
public class GenericsKbAVLApp
{
    /**
     * Stores a {@link AVLTree} Object of type {@link Generics}
     */
    private static AVLTree<Generics> avl = new AVLTree<>();
    private static List<String> entries = new ArrayList<>();
    private static List<String> query = new ArrayList<>();

    public GenericsKbAVLApp()
    {
        read("GenericsKB-queries.txt");
    }

    /**
     * Method to read in data from file
     */
    public static void read(String q)
    {
        try
        {
            //Start - code used to find the path of the text files
            String relativePath = "lib/GenericsKB.txt";
            String relativePath1 = "Assignment_2/lib/GenericsKB.txt";
            String relativePath2 = "lib/"+q;
            String relativePath3 = "Assignment_2/lib/"+q;
            File fileTemp = new File("");
            String absolutePath = "";
            String absolutePath1 = "";
            if (fileTemp.getAbsolutePath().contains("Assignment_2"))
            {
                File file = new File(relativePath);
                absolutePath = file.getAbsolutePath();
                file = new File(relativePath2);
                absolutePath1 = file.getAbsolutePath();
            } else
            {
                File file = new File(relativePath1);
                absolutePath = file.getAbsolutePath();
                file = new File(relativePath3);
                absolutePath1 = file.getAbsolutePath();
            }
            //end
            System.out.println(absolutePath);
            BufferedReader ff = new BufferedReader(new FileReader(absolutePath));
            String line;
            while ((line = ff.readLine()) != null)
            {
                entries.add(line);
                String temp[] = line.split("\\t");
                String term = temp[0];
                String sentence = temp[1];
                double confidence = Double.parseDouble(temp[2]);
                avl.insert(new Generics(term, sentence, confidence));

            }
            ff = new BufferedReader(new FileReader(absolutePath1));
            while ((line = ff.readLine()) != null)
            {
                query.add(line);
            }
            ff.close();
        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args)
    {
        read("Part1.txt");
        String line;
        for (int i = 0; i < query.size(); i++)
        {
            line = query.get(i);
            AVLTreeNode<Generics> node = avl.search(new Generics(line, "", -1));
            if (node != null)
                System.out.println(node.getData().toString());
            else
                System.out.println("The term: " + line + " ,is not in the knowledge base");
        }
    }

    /**
     * Method to populate experimental data
     * @param size The size of the AVLTree to be tested
     */
    public void experimentSearch(int size)
    {
        System.out.println("Start search: "+ size);
        String relativePath = "lib/Assignment2.accdb";
        String relativePath1 = "Assignment_2/lib/Assignment2.accdb";
        File fileTemp = new File("");
        String absolutePath = "";
        if (fileTemp.getAbsolutePath().contains("Assignment_2"))
        {
            File file = new File(relativePath);
            absolutePath = file.getAbsolutePath();
        }else
        {
            File file = new File(relativePath1);
            absolutePath = file.getAbsolutePath();
        }
        String tableName = "Search";
        try(Database db = new DatabaseBuilder().setFile(new File(absolutePath)).setAutoSync(false).open())
        {
            AVLTree<Generics> avl = new AVLTree<>();
            //Shuffles the entries so a random subset can be pulled
            Collections.shuffle(entries);
            //
            String line;
            for (int i = 0; i < size; i++)
            {
                line = entries.get(i);
                String temp[] = line.split("\\t");
                String term = temp[0];
                String sentence = temp[1];
                double confidence = Double.parseDouble(temp[2]);
                avl.insert(new Generics(term, sentence, confidence));
            }
            query.remove(query.size()-1);
            AVLTreeNode<Generics> first = avl.getRoot();
            query.add(0,first.getData().getTerm());
            for (int i = 0; i < query.size(); i++)
            {
                AVLTreeNode<Generics> node = avl.search(new Generics(query.get(i), "", -1));
                Table tbl = db.getTable(tableName);
                //tbl.addRow(Column.AUTO_NUMBER,size,avl.searchCounter);
                tbl.addRow(size,avl.searchCounter);
                if(node ==null)
                {
                    avl.insertCounter=0;
                    avl.insert(new Generics(query.get(i), "", -1));
                    tbl = db.getTable("Insert");
                    //tbl.addRow(Column.AUTO_NUMBER,size,avl.insertCounter);
                    tbl.addRow(size,avl.insertCounter);
                    avl.delete(new Generics(query.get(i), "", -1));
                }
                avl.searchCounter = 0;
            }
            System.out.println("done search: "+ size);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}