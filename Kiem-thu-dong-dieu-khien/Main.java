import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.*;

class Delivery {
  public static double deliveryFee(double a, double b) {
    if (a < 0 || a > 10 || b < 0 || b > 20) {
      throw new IllegalArgumentException("Giá trị không hợp lệ");
    }
    double ans = 0;
    if (a < 5) ans = 20000;
    else ans = 40000;
    if (b < 5) ans += 20000;
    else if (b < 10) ans += (b * 4000);
    else ans += (b * 6000);
    return ans;
  }
}

public class Main {
  public static void main(String[] args) {
    Object[][] tests = {
            {-1.0, 10.0, "Giá trị không hợp lệ"},
            {2.0, 3.0, 40000.0},
            {4.0, 8.0, 52000.0},
            {1.0, 16.0, 116000.0},
            {5.0, 3.0, 60000.0},
            {8.0, 7.0, 68000.0},
            {10.0, 15.0, 130000.0}
    };

    for (int i = 0; i < tests.length; i++) {
      double a = (double) tests[i][0];
      double b = (double) tests[i][1];

      Object expected = tests[i][2];
      String status;
      String outputStr;

      try {
        double output = Delivery.deliveryFee(a, b);
        outputStr = String.format("%.0f", output);


        if (expected instanceof Double && Math.abs(output - (double) expected) < 0.001) {
          status = "PASS";
        } else {
          status = "FAIL";
        }

        System.out.printf("test_id:%d a:%.1f b:%.1f output:%.0f expected:%.0f result:%s%n",
                i + 1, a, b, output, (double) expected, status);
      } catch (IllegalArgumentException e) {
        if (expected instanceof String && expected.equals(e.getMessage())) {
          status = "PASS";
        } else {
          status = "FAIL";
        }

        System.out.printf("test_id:%d a:%.1f b:%.1f output:%s expected:%s result:%s%n",
                i + 1, a, b, e.getMessage(), expected, status);
      }
    }
  }
}


/*
-1 10
2 3
4 8
1 16
5 3
8 7
10 15
 */