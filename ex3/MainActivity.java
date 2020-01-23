package com.example.ex3;



import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText et_input;
    Stack<String> input;
    ArrayList<String> post_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_input=findViewById(R.id.et_input);
        input=new Stack<String>();
        post_input=new ArrayList<String>();
        String ops[]={"c","del","dot","plus","minus","mul","div","percent","open","close","equal","sin","cos","log"};
        for(int i=0;i<10;i++){
            int id=getResources().getIdentifier("tv_"+Integer.toString(i),"id",getPackageName());
            findViewById(id).setOnClickListener(this);
        }
        for(int i=0;i<14;i++){
            int id=getResources().getIdentifier("tv_"+ops[i],"id",getPackageName());
            View temp = findViewById(id);
            try {
                temp.setOnClickListener(this);
            } catch (NullPointerException e) {
                System.out.println(i+"\n\n\n\n\n\n\n\n\n\n\n\n\n");
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_c:
                et_input.setText("");
                break;
            case R.id.tv_del:
                String val=et_input.getEditableText().toString();
                et_input.setText(val.substring(0,val.length()-1));
                break;
            case R.id.tv_equal:
                String ip=et_input.getEditableText().toString();
                if(!ip.startsWith("sin") && !ip.startsWith("cos") && !ip.startsWith("log")) {
                    postFix(ip);
                    calc();
                    et_input.setText(input.peek());
                }
                else
                    trig_calc(ip);
                break;
            default:
                TextView tv_num=(TextView)v;
                et_input.setText(et_input.getEditableText().toString() + tv_num.getText());
                break;
        }
        et_input.setSelection(et_input.getEditableText().length());
    }
    private void postFix(String ip){
        makeEmpty();
        post_input.clear();
        ip = ip.replaceAll("[+|-|x|/|(|)]", ",$0,");
        ip = ip.replaceAll(",,", ",");
        String sep[]=ip.split(",");
        for(int i=0;i<sep.length;i++){
            if(sep[i].equals("x") || sep[i].equals("(") || sep[i].equals("+") || sep[i].equals("-") ||
                    sep[i].equals("/") || sep[i].equals("%"))
                input.push(sep[i]);
            else if(sep[i].equals(")")){
                while(!input.peek().equals("("))
                    post_input.add(input.pop());
                input.pop();
            }
            else
                post_input.add(sep[i]);
        }
        while(!input.isEmpty())
            post_input.add(input.pop());
    }
    private void calc(){
        makeEmpty();
        for(String num:post_input){
            if(num.equals("x") || num.equals("+") || num.equals("-") || num.equals("/") ||
                    num.equals("%")){
                String va1_a=input.pop();
                String va1_b=input.pop();
                float a,b;
                try {
                    a = Integer.parseInt(va1_a);
                }
                catch (Exception e){
                    a = Float.parseFloat(va1_a);
                }
                try {
                    b = Integer.parseInt(va1_b);
                }
                catch (Exception e){
                    b = Float.parseFloat(va1_b);
                }
                switch (num){
                    case "+":
                        input.push(Float.toString(b+a));
                        break;
                    case "-":
                        input.push(Float.toString(b-a));
                        break;
                    case "x":
                        input.push(Float.toString(b*a));
                        break;
                    case "/":
                        input.push(Float.toString(b/a));
                        break;
                    case "%":
                        input.push(Float.toString(b%a));
                        break;
                }
            }
            else
                input.push(num);
        }
    }
    private void trig_calc(String ip){
        Double val = Double.parseDouble(ip.substring(3));
        if(ip.startsWith("sin"))
            et_input.setText(Double.toString(Math.sin(Math.toRadians(val))));
        else if(ip.startsWith("cos"))
            et_input.setText(Double.toString(Math.cos(Math.toRadians(val))));
        else if(ip.startsWith("log"))
            et_input.setText(Double.toString(Math.log(val)));
    }
    private void makeEmpty(){
        while (!input.isEmpty())
            input.pop();
    }
}
