

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class queryFactory {
  public queryFactory() {
  }

  public static String gKtest()
  {
	  return "select Qstatement,Answer,Options from Questions where QuestionID LIKE 'g%'";
  }

  public static String getIDs()
  {
	  return "select UserID from Users";
  }

}
