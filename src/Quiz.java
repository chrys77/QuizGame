import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz implements ActionListener {

    String[] questions = {
            "Which company created Java?",
            "Which ear was Java created",
            "What was Java originally called",
            "Who is credited with creating Java"
    };
    String[][] options = {
            {"Sun Microsystems", "Starbucks", "Microsoft", "Alphabet"},
            {"1989", "1996", "1972", "1492"},
            {"Apple", "Latte", "Oak", "Koffing"},
            {"Steve Jobs", "Bill Gates", "James Gosling", "Mark Zuckerburg"}

    };
    char[] answers = {'A', 'B', 'C', 'C'};
    char answer;
    int index;
    int correctGuesses = 0;
    int totalQuestions = questions.length;
    int result;
    int seconds = 10;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answerLabelA = new JLabel();
    JLabel answerLabelB = new JLabel();
    JLabel answerLabelC = new JLabel();
    JLabel answerLabelD = new JLabel();
    JLabel timeLabel = new JLabel();
    JLabel secondsLeft = new JLabel();
    JTextField numberRight = new JTextField();
    JTextField percentage = new JTextField();


    Timer timer = new Timer(2000, e -> {

        seconds--;
        secondsLeft.setText(String.valueOf(seconds));
        if (seconds <= 0) {
            displayAnswer();

        }
    });


    public Quiz() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.setTitle("Quizz game, by Dobro");
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setResizable(false);

        textField.setBounds(0, 0, 650, 50);
        textField.setBackground(Color.DARK_GRAY);
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Ink Free", Font.BOLD, 30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        textArea.setBounds(0, 50, 650, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("MV Boli", Font.BOLD, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        buttonA.setBounds(0, 100, 100, 100);
        buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 200, 100, 100);
        buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 300, 100, 100);
        buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 400, 100, 100);
        buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answerLabelA.setBounds(125, 100, 500, 100);
        answerLabelA.setForeground(Color.WHITE);
        answerLabelA.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answerLabelB.setBounds(125, 200, 500, 100);
        answerLabelB.setForeground(Color.WHITE);
        answerLabelB.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answerLabelC.setBounds(125, 300, 500, 100);
        answerLabelC.setForeground(Color.WHITE);
        answerLabelC.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answerLabelD.setBounds(125, 400, 500, 100);
        answerLabelD.setForeground(Color.WHITE);
        answerLabelD.setFont(new Font("MV Boli", Font.PLAIN, 35));

        secondsLeft.setBounds(535, 510, 100, 100);
        secondsLeft.setBackground(Color.DARK_GRAY);
        secondsLeft.setForeground(Color.RED);
        secondsLeft.setFont(new Font("Ink Free", Font.BOLD, 60));
        secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
        secondsLeft.setOpaque(true);
        secondsLeft.setHorizontalAlignment(JTextField.CENTER);
        secondsLeft.setText(String.valueOf(seconds));

        timeLabel.setBounds(535, 475, 100, 25);
        timeLabel.setForeground(Color.GREEN);
        timeLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setText("timer");

        numberRight.setBounds(225, 225, 200, 100);
        numberRight.setBackground(Color.DARK_GRAY);
        numberRight.setForeground(Color.WHITE);
        numberRight.setFont(new Font("Ink Free", Font.BOLD, 50));
        numberRight.setBorder(BorderFactory.createBevelBorder(1));
        numberRight.setHorizontalAlignment(JTextField.CENTER);
        numberRight.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(Color.DARK_GRAY);
        percentage.setForeground(Color.WHITE);
        percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        frame.add(timeLabel);
        frame.add(secondsLeft);
        frame.add(answerLabelA);
        frame.add(answerLabelB);
        frame.add(answerLabelC);
        frame.add(answerLabelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);

        nextQuestion();

    }


    public void nextQuestion() {

        if (index >= totalQuestions) {
            results();
        } else {
            textField.setText("Question " + (index + 1));
            textArea.setText(questions[index]);
            answerLabelA.setText(options[index][0]);
            answerLabelB.setText(options[index][1]);
            answerLabelC.setText(options[index][2]);
            answerLabelD.setText(options[index][3]);
            timer.start();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (e.getSource() == buttonA) {
            answer = 'A';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        }

        if (e.getSource() == buttonB) {
            answer = 'B';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        }

        if (e.getSource() == buttonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        }

        if (e.getSource() == buttonD) {
            answer = 'D';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        }

        displayAnswer();

    }

    public void displayAnswer() {

        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] != 'A') {
            answerLabelA.setForeground(Color.RED);
        } else {
            answerLabelA.setForeground(Color.GREEN);
        }
        if (answers[index] != 'B') {
            answerLabelB.setForeground(Color.RED);
        } else {
            answerLabelB.setForeground(Color.GREEN);
        }
        if (answers[index] != 'C') {
            answerLabelC.setForeground(Color.RED);
        } else {
            answerLabelC.setForeground(Color.GREEN);
        }
        if (answers[index] != 'D') {
            answerLabelD.setForeground(Color.RED);
        } else {
            answerLabelD.setForeground(Color.GREEN);
        }

        Timer pause = getTimer();
        pause.start();

    }

    private Timer getTimer() {
        Timer pause = new Timer(2000, e -> {

            answerLabelA.setForeground(Color.WHITE);
            answerLabelB.setForeground(Color.WHITE);
            answerLabelC.setForeground(Color.WHITE);
            answerLabelD.setForeground(Color.WHITE);

            answer = ' ';
            seconds = 10;
            secondsLeft.setText(String.valueOf(seconds));
            buttonA.setEnabled(true);
            buttonB.setEnabled(true);
            buttonC.setEnabled(true);
            buttonD.setEnabled(true);
            index++;
            nextQuestion();
        });

        pause.setRepeats(false);
        return pause;
    }

    public void results() {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int) ((correctGuesses / (double) totalQuestions) * 100);

        textField.setText(("RESULTS!"));
        textArea.setText("");
        answerLabelA.setText("");
        answerLabelB.setText("");
        answerLabelC.setText("");
        answerLabelD.setText("");

        numberRight.setText(correctGuesses + " / " + totalQuestions);
        percentage.setText(result + "%");

        frame.add(numberRight);
        frame.add(percentage);

    }

}
