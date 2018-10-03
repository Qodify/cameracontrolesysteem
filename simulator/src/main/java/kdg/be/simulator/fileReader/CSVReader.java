package kdg.be.simulator.fileReader;

import javafx.scene.Camera;
import kdg.be.simulator.models.CameraMessage;
import org.apache.commons.jexl3.JxltEngine;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Component
public class CSVReader {
  private static final char DEFAULT_SEPARATOR = ',';
  private static final char DEFAULT_QUOTE = '"';
  private Scanner scanner;

  public CSVReader() {
  }

  public void Initialize() {

  }

//  public CameraMessage GetCSVItem(String path) {
//
//
//    try {
//      scanner = new Scanner(new File(path));
//      while (scanner.hasNext()) {
//        List<String> line = parseLine(scanner.nextLine());
//        return new CameraMessage(Integer.parseInt(line.get(0)), line.get(1), LocalDateTime.now().plusSeconds(Long.parseLong(line.get(2)) / 1000));
//      }
//    } catch (
//        FileNotFoundException e) {
//      e.printStackTrace();
//    }
//    scanner.close();
//    return null;
//  }


  public static List<String> parseLine(String cvsLine) {
    return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
  }

  public static List<String> parseLine(String cvsLine, char separators) {
    return parseLine(cvsLine, separators, DEFAULT_QUOTE);
  }

  public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

    List<String> result = new ArrayList<>();

    //if empty, return!
    if (cvsLine == null && cvsLine.isEmpty()) {
      return result;
    }

    if (customQuote == ' ') {
      customQuote = DEFAULT_QUOTE;
    }

    if (separators == ' ') {
      separators = DEFAULT_SEPARATOR;
    }

    StringBuffer curVal = new StringBuffer();
    boolean inQuotes = false;
    boolean startCollectChar = false;
    boolean doubleQuotesInColumn = false;

    char[] chars = cvsLine.toCharArray();

    for (char ch : chars) {

      if (inQuotes) {
        startCollectChar = true;
        if (ch == customQuote) {
          inQuotes = false;
          doubleQuotesInColumn = false;
        } else {

          //Fixed : allow "" in custom quote enclosed
          if (ch == '\"') {
            if (!doubleQuotesInColumn) {
              curVal.append(ch);
              doubleQuotesInColumn = true;
            }
          } else {
            curVal.append(ch);
          }

        }
      } else {
        if (ch == customQuote) {

          inQuotes = true;

          //Fixed : allow "" in empty quote enclosed
          if (chars[0] != '"' && customQuote == '\"') {
            curVal.append('"');
          }

          //double quotes in column will hit this!
          if (startCollectChar) {
            curVal.append('"');
          }

        } else if (ch == separators) {

          result.add(curVal.toString());

          curVal = new StringBuffer();
          startCollectChar = false;

        } else if (ch == '\r') {
          //ignore LF characters
          continue;
        } else if (ch == '\n') {
          //the end, break!
          break;
        } else {
          curVal.append(ch);
        }
      }

    }

    result.add(curVal.toString());

    return result;
  }
}
