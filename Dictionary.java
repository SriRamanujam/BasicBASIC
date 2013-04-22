package Final;

public class Dictionary {

  private HashVar[] data;


  public Dictionary() {
    data = new HashVar[103];

    for (int i = 0; i < data.length; i++) {
      data[i] = new HashVar("0", 0.0);
    }

  }

  public void put(String s, Double d) {

    int key = hash(s);

    if (data[key] == null) {
      System.out.println("nothing in this bin");
      data[key] = new HashVar(s, d);
      return;
    } else {
      System.out.println("Already stuff in this bin");
      HashVar hashElement = data[key];

      while (hashElement.link != null ) {

        if (hashElement.link.data.equals(s)) {
          System.out.println("Found duplicate");
          HashVar temp = new HashVar(s,d);
          temp.link = hashElement.link.link;
          hashElement.link = temp;
          return;
        }  
        hashElement = hashElement.link;

      }
      System.out.println("No dupes, gonna make a new one");
      hashElement.link = new HashVar(s, d);
      return;
    }
  }

  private int hash(String s) {
    int j = 0;
    int total = 0;

    if (s.length() == 1) {
      char letter = s.charAt(0);
      int num = (int) letter;
      num -= 8;
      return num % data.length;
    } else if (s.length() % 2 == 0) {
      j = s.length();
    } else {
      j = s.length() - 1;
    }

    for (int i = 0; i < j; i=i+2) {
      char letter1 = s.charAt(i);
      char letter2 = s.charAt(i+1);

      int num1 = (int) letter1;
      int num2 = (int) letter2;

      num1 = num1 - 8; 
      int orredBytes = num1 + num2; 
      total = total + orredBytes;
    }

    total--;
    return total % data.length;
  }

  public Double get(String s) {
    int key = hash(s);
    HashVar temp = data[key];

    while (temp != null && !(temp.data.equals(s))) {
      temp = temp.link;
    }
    if (temp == null) {
      throw new HashException("Key doesn't exist in database, something's wrong.");
    } else {
      return temp.value;
    }
  }
}

class HashVar {

  Double value;
  String data;
  HashVar link;

  public HashVar(String data, Double value) {
    this.value= value;
    this.data = data;
    link = null;
  }
}