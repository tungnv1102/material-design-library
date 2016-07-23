package vn.edu.benchmarking.app.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import vn.edu.benchmarking.app.R;

public class AdvisingFragment extends vn.edu.benchmarking.fragments.AFragment {
    private Spinner tinh, khoi;
    private EditText tv_diem;
    private Button btnsearch;
    private ListView lvresult;

    public AdvisingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advising, container, false);
        btnsearch = (Button)view.findViewById(R.id.btnSearch);
        tv_diem = (EditText)view.findViewById(R.id.tv_mark);
        tinh = (Spinner)view.findViewById(R.id.spinner_tinh);
        khoi = (Spinner)view.findViewById(R.id.spinner_khoi);
        lvresult = (ListView)view.findViewById(R.id.lvresult);
        final Context context = this.getActivity();
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d = String.valueOf(tv_diem.getText());
                String k = String.valueOf(khoi.getSelectedItem().toString());
                k = k.substring(0,k.indexOf(" "));
                String t = String.valueOf(tinh.getSelectedItem().toString());
                t = t.substring(0,t.indexOf(" "));
                Log.d("tungnv", new StringBuilder().append(d).append(k).append(t).toString());
                try {
                    ArrayList<String> result = new getMarksTasks().execute(getUrl(d, k, t)).get();
                    ArrayAdapter adapter = new ArrayAdapter(context,R.layout.listview_row,result);
                    lvresult.setAdapter(adapter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
    private String getUrl(String diem, String Khoi, String Tinh)
    {
        return String.format(new StringBuilder().append("http://diemthi.tuyensinh247.com/tu-van-")
                .append("chon-truong/tinh%s-loaidaotao0-bacdaotao0-tongdiem%s-khoi%s/").toString(), Tinh, diem, Khoi );
    }
    private class getMarksTasks extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String link= params[0];
            String __url="";
            ArrayList<String> result = new ArrayList<>();
            try {
                int page = 1;
                __url = String.format("%s%s", link, String.format("%d.html", page));
                URL url = new URL(__url);
                Document doc = Jsoup.parse(url,30000);
                Elements a = doc.select("div.box_listmark > ul li a");
                while (a.size() > 0)
                {
                    for (Element element : a){
                        result.add(element.text());
                    }
                    page++;
                    __url = String.format("%s%s", link, String.format("%d.html", page));
                    url = new URL(__url);
                    doc = Jsoup.parse(url,30000);
                    a = doc.select("div.box_listmark > ul li a");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
