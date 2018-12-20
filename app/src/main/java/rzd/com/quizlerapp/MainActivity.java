package rzd.com.quizlerapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnTrue1, btnFalse1;
    TextView txtViewQuestions1, txtViewScore1;
    ProgressBar progBar11;
    int mQuestions , mIndex, mScore;

    private TrueFalse[] mQuestionArray = new  TrueFalse[]{
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4,true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6,false),
            new TrueFalse(R.string.question_7,true),
            new TrueFalse(R.string.question_8,false),
            new TrueFalse(R.string.question_9,true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11,false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)

    };
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionArray.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");

        }else {
            mScore = 0;
            mIndex = 0;

        }

        btnFalse1 = findViewById(R.id.btnFalse);
        btnTrue1 = findViewById(R.id.btnTrue);
        txtViewQuestions1 = findViewById(R.id.txtViewQuestions);
        txtViewScore1 = findViewById(R.id.txtViewScore);
        progBar11 = findViewById(R.id.progBar1);

        txtViewQuestions1.setText(mQuestionArray[mIndex].getQuestions());
        txtViewScore1.setText("Score: " + mScore + "/" + mQuestionArray.length);


        btnFalse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              checkAnswer(false);
              updateQuestions();
            }
        });
        btnTrue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestions();
            }
        });
    }

    private void updateQuestions(){
        mIndex = (mIndex + 1) % mQuestionArray.length;
        if(mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("Your score " + mScore + " points.");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
          alert.show();
        }
        mQuestions = mQuestionArray[mIndex].getQuestions();
        txtViewQuestions1.setText(mQuestions);
        progBar11.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        txtViewScore1.setText("Score: " + mScore + "/" + mQuestionArray.length);
    }

    private void checkAnswer(boolean userAnswer){

        boolean correctAnswer = mQuestionArray[mIndex].isAnswer();
        if(userAnswer == correctAnswer){
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
            mScore = mScore + 1;

        }
        else{
            Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);

    }

}
