import javax.swing.JOptionPane;

public class SMS {
  private String[] inbox;
  private int smsCount;
  private int capacity;
  private int balance;

  public SMS() {
    this.capacity = 10;
    this.inbox = new String[capacity];
    this.smsCount = 0;
    this.balance = 0;
  }

  public SMS(int capacity) {
    this.capacity = capacity;
    this.inbox = new String[capacity];
    this.smsCount = 0;
    this.balance = 0;
  }

  public boolean isFull() {
    return smsCount == capacity;
  }

  public boolean isEmpty() {
    return smsCount == 0;
  }

  public int getLoadBalance() {
    return balance;
  }

  public int totalSMS() {
    return smsCount;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setSMS(String sms) {
    if (balance > 0 && !isFull()) {
      inbox[smsCount] = sms;
      smsCount++;
      balance--;
    }
  }

  public String viewSMS() {
    if (!isEmpty()) {
      StringBuilder messages = new StringBuilder();
      for (int i = 0; i < smsCount; i++) {
        messages.append("SMS ").append(i + 1).append(": ").append(inbox[i]).append("\n");
      }
      return messages.toString();
    }
    return "Inbox is empty.";
  }

  public void deleteSMS(int index) {
    if (!isEmpty() && index >= 0 && index < smsCount) {
      for (int i = index; i < smsCount - 1; i++) {
        inbox[i] = inbox[i + 1];
      }
      inbox[smsCount - 1] = null;
      smsCount--;
    }
  }

  public String searchSMS(int index) {
    if (!isEmpty() && index >= 0 && index < smsCount) {
      return inbox[index];
    }
    return "Invalid index or inbox is empty.";
  }

  public void clear() {
    if (!isEmpty()) {
      inbox = new String[capacity];
      smsCount = 0;
    }
  }

  public void setLoad(int amount) {
    balance += amount;
  }

  public void setCapacity(int capacity) {
    if (capacity >= smsCount) {
      String[] newInbox = new String[capacity];
      System.arraycopy(inbox, 0, newInbox, 0, smsCount);
      inbox = newInbox;
      this.capacity = capacity;
    }
  }

  public static void main(String[] args) {
    SMS smsSystem = new SMS(5);

    smsSystem.setLoad(10);
    smsSystem.setSMS("Hello, how are you?");
    smsSystem.setSMS("I'm doing great!");

    JOptionPane.showMessageDialog(null, "Is inbox full? " + smsSystem.isFull());
    JOptionPane.showMessageDialog(null, "Is inbox empty? " + smsSystem.isEmpty());
    JOptionPane.showMessageDialog(null, "Load balance: " + smsSystem.getLoadBalance());
    JOptionPane.showMessageDialog(null, "Total SMS: " + smsSystem.totalSMS());
    JOptionPane.showMessageDialog(null, "Inbox capacity: " + smsSystem.getCapacity());
    JOptionPane.showMessageDialog(null, "Viewing SMS:\n" + smsSystem.viewSMS());

    smsSystem.setSMS("This is another message.");
    smsSystem.setSMS("One more message.");

    JOptionPane.showMessageDialog(null, "Is inbox full? " + smsSystem.isFull());
    JOptionPane.showMessageDialog(null, "Total SMS: " + smsSystem.totalSMS());

    JOptionPane.showMessageDialog(null, "Deleting SMS at index 1...");
    smsSystem.deleteSMS(1);

    JOptionPane.showMessageDialog(null, "Searching SMS at index 0: " + smsSystem.searchSMS(0));

    JOptionPane.showMessageDialog(null, "Viewing SMS:\n" + smsSystem.viewSMS());

    JOptionPane.showMessageDialog(null, "Clearing the inbox...");
    smsSystem.clear();

    JOptionPane.showMessageDialog(null, "Is inbox empty? " + smsSystem.isEmpty());
  }
}
