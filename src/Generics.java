package Assigntment2.src;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Class used to store the term, the generic sentence and the confidence score
 * @author Chidiebere Umah
 */
public class Generics implements Comparable<Generics>
{
        /**
         * The variable storing a term in the knowledge base
         */
        private String term;
        /**
         * The sentence describing the term
         */
        private String sentence;
        /**
         * The confidence score of the term
         */
        private double confidence_score;

        /**
         * Constructer method of Generic Object
         * @param term the term incoming
         * @param sentence the sentence incoming
         * @param confidence_score the confidence score incoming
         */
        public Generics(String term,String sentence, double confidence_score)
        {
            this.term = term;
            this.sentence = sentence;
            this.confidence_score = confidence_score;
        }

        /**
         * copy constructor
         * @param other another Generics Object to make a copy
         */
        public Generics(Generics other)
        {
            new Generics(other.term,other.sentence,other.confidence_score);
        }

        /**
         * @return the value of the term
         */
        public String getTerm()
        {
            return term;
        }

        /**
         * @return the value of sentence of the term
         */
        public String getSentence()
        {
            return sentence;
        }

        /**
         * @return The confidence score of the term
         */
        public double getConfidence_score()
        {
            //Return confidence score rounded of to 3 decimal places
            DecimalFormat deci = new DecimalFormat("0.000");
            deci.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
            confidence_score = Double.parseDouble(deci.format(confidence_score));
            return confidence_score;
        }

        /**
         * Method that checks if the term parsed through is the same as the term of the Generic object
         * @param term incoming term value
         * @return True if terms are the same, False otherwise
         */
        public boolean equals(String term)
        {
            return this.term.equalsIgnoreCase(term);
        }

        /**
         * Method to update the sentence and the confidence score of the Object
         * @param inSentence incoming Sentence
         * @param inConfidence incoming confidence score
         */
        public void update(String inSentence,double inConfidence)
        {
            sentence = inSentence;
            confidence_score = inConfidence;
        }

        /**
         *
         * @return Values sentence and Confidence Score of the object in the format "[Sentence] (Confidence score: [confidence score])
         */
        public String toString()
        {
            DecimalFormat deci = new DecimalFormat("0.00");
            //change decimal separator to a point(.)
            deci.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
            return sentence + " (Confidence score: "+ deci.format(confidence_score) +")";
        }

        /**
         * @param other the object to be compared.
         * @return which term is bigger/smaller
         */
        public int compareTo(Generics other)
        {
            return term.toLowerCase().compareTo(other.term.toLowerCase());
        }
}
