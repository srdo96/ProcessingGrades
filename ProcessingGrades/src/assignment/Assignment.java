package assignment;
 
import java.io.*;
 
public class Assignment {
 
    // demo data: https://docs.google.com/spreadsheets/d/1wEig2oahs2sjcXk93YUhHs92fqG7AXFucDw2kRK0S2I/edit?usp=sharing
    void printArray(double data[], int length) {
        for (int i = 0; i < length; i++) {
            System.out.println(i + " " + data[i]);
        }
    }
    void histrogram(int data[]) {
        System.out.println("Histrogram");
        System.out.println("A+: "+data[0]);
        System.out.println("A : "+data[1]);
        System.out.println("A-: "+data[2]);
        System.out.println("B+: "+data[3]);
        System.out.println("B : "+data[4]);
        System.out.println("B-: "+data[5]);
        System.out.println("C+: "+data[6]);
        System.out.println("C : "+data[7]);
        System.out.println("D : "+data[8]);
        System.out.println("F : "+data[9]);
    }
 
    double min(double data[], int length) {
        double minValue = Double.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            minValue = Math.min(data[i], minValue);
        }
        return minValue;
    }
 
    double max(double data[], int length) {
        double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            maxValue = Math.max(data[i], maxValue);
        }
        return maxValue;
    }
 
    double average(double data[], int length) {
        double s = 0.0;
        for (int i = 0; i < length; i++) {
            s = s + data[i];
        }
        return s / length;
    }
    double sum(double data[], int length) {
        double s = 0.0;
        for (int i = 0; i < length; i++) {
            s = s + data[i];
        }
        return s;
    }
   
    double stdDev(double data[], int length){
        double ave = average(data, length);
        double s = 0.0;
        for(int i = 0; i < length; i++){
            double sub = data[i] - ave;
            double p = Math.pow(sub, 2);
            s = Double.sum(p, s);
        }
        return Math.sqrt(s/length);
    }
/*
    double sum(double data[], int length) {
        double s = 0.0;
        for (int i = 0; i < length; i++) {
            s = s + data[i];
        }
        return s;
    }
*/
    int grade(double totalMark) {
        int num;
        if (totalMark >= 80) {
            num = 0;
        } else if (totalMark >= 75) {
            num = 1;
        } else if (totalMark >= 70) {
            num = 2;
        } else if (totalMark >= 65) {
            num = 3;
        } else if (totalMark >= 60) {
            num = 4;
        } else if (totalMark >= 55) {
            num = 5;
        } else if (totalMark >= 50) {
            num = 6;
        } else if (totalMark >= 45) {
            num = 7;
        } else if (totalMark >= 40) {
            num = 8;
        } else {
            num = 9;
        }
 
        return num;
    }
   
    void print(String title, double arg[], int length){
        System.out.println(title);
        System.out.println("Minimum Mark: "+min(arg, length));
        System.out.println("Maximum Mark: "+max(arg, length));
        System.out.printf("Avrage Mark: %.2f\n",average(arg, length));
        System.out.printf("Standard Deviation: %.2f\n",stdDev(arg, length));
        System.out.println("");
    }
 
    public Assignment() {
        readDataFromFile("data.csv");
    }
 
    public void readDataFromFile(String filename) {
        // try with resource
        try (RandomAccessFile input = new RandomAccessFile(filename, "r")) {
            String line;
 
            // headers/metadata
            line = input.readLine();
        //    System.out.println(line);
            line = input.readLine();
        //    System.out.println(line);
 
            int n = 10000;
            int id[] = new int[n];
            String name[] = new String[n];
            double absent[] = new double[n];
            double assessment[] = new double[n];
            double attendanceMark[] = new double[n];
            double Q1[] = new double[n];
            double Q2[] = new double[n];
            double Q3[] = new double[n];
            double Q4[] = new double[n];
            double Q5[] = new double[n];
            double midTerm[] = new double[n];
            double finalTerm[] = new double[n];
            double totalMark[] = new double[n];
            int gradeCount[] = new int[n];
 
            int j = 0;
            while (true) {
                line = input.readLine();
                if (line == null) {
                    break;
                }
                String tokens[] = line.split("\\,");
                //all empty cell become 0
                for (int i = 3; i <= 9; i++) {
                    if (tokens[i].equals("")) {
                        tokens[i] = "0";
                    }
                }
 
                id[j] = Integer.parseInt(tokens[0]);
                name[j] = tokens[1];
                absent[j] = Integer.parseInt(tokens[2]);
                //calculate attendance Mark
                double attendance = 5.0;
                for (int i = 2; i <= absent[j]; i++) {
                    attendance = attendance - 0.5;
                }
                attendanceMark[j] = attendance;
 
                //all Quizz marks storing Q(1-5)[]
                Q1[j] = Double.parseDouble(tokens[3]);
                Q2[j] = Double.parseDouble(tokens[4]);
                Q3[j] = Double.parseDouble(tokens[5]);
                Q4[j] = Double.parseDouble(tokens[6]);
                Q5[j] = Double.parseDouble(tokens[7]);
                double Q[] = {Q1[j], Q2[j], Q3[j], Q4[j], Q5[j]};
                double best4Sum = sum(Q,5) - min(Q,5);//
                assessment[j] = best4Sum / 40 * 25;
 
                midTerm[j] = Double.parseDouble(tokens[8]);
                finalTerm[j] = Double.parseDouble(tokens[9]);
                totalMark[j] = attendanceMark[j] + assessment[j] + midTerm[j] + finalTerm[j];
                totalMark[j] = Math.round(totalMark[j] * 100) / 100.0; //give two decimal after dot(.)
                int grade = grade(totalMark[j]);
                gradeCount[grade]++;
               
 
                j++;
            }//end while loop
            //printArray(attendanceMark,j);
            print("Attendance", attendanceMark, j);
            print("Quiz 1", Q1, j);
            print("Quiz 2", Q2, j);
            print("Quiz 3", Q3, j);
            print("Quiz 4", Q4, j);
            print("Quiz 5", Q5, j);
            print("Assessment", assessment, j);
            print("Mid Term", midTerm, j);
            print("Final Term", finalTerm, j);
            print("Total Mark", totalMark, j);
            histrogram(gradeCount);
            //double d[] = {2,4,4,4,5,5,7,9};
            //System.out.println(stdDev(d,8));
            //printArray(totalMark, j);
           
           
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found");
        } catch (IOException ioe) {
            System.err.println("IOException occured");
        }
 
    }//end readDataFromFile
 
    public static void main(String[] args) {
        new Assignment();
    }
 
}//end class