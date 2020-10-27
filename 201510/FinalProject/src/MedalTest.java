import javax.swing.JFrame;

public class MedalTest 
{
   public static void main( String args[] )
   { 
      Medal labelFrame = new Medal(); // create LabelFrame
     // labelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      labelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      labelFrame.setSize(350,930);
	  labelFrame.setResizable(false); 
      labelFrame.setVisible(true);
   } // end main
} // end class