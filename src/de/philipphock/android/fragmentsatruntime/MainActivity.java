package de.philipphock.android.fragmentsatruntime;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements HeadlinesFragment.OnHeadlineSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if(findViewById(R.id.fragment_container) != null){
        	if (savedInstanceState != null){
        		//restored, the fragment exists
        		return;
        	}
        	
        	
        	HeadlinesFragment firstElement = new HeadlinesFragment();
        	firstElement.setArguments(getIntent().getExtras());
        	getFragmentManager().beginTransaction()
        		.add(R.id.fragment_container, firstElement).commit();
        	
        	
        	
        }else{
        	
        }
    }

	@Override
	public void onArticleSelected(int position) {
		
		ArticleFragment articleFrag = (ArticleFragment) getFragmentManager().findFragmentById(R.id.article_fragment);
		if (articleFrag!=null){
			//large layout
			articleFrag.updateArticleView(position);
		}else{
			//small layout
			ArticleFragment newFragment = new ArticleFragment();
			Bundle args = new Bundle();
			args.putInt(ArticleFragment.ARG_POSITION, position);
			newFragment.setArguments(args);
			
			FragmentTransaction trans = getFragmentManager().beginTransaction();
			trans.replace(R.id.fragment_container, newFragment);
			trans.addToBackStack(null);
			trans.commit();
		}
		}
		

    
}
