package com.example.soft1706081301317sweepacticity;		 package com.example.soft1706081301317sweepacticity;

 
  import android.content.Intent;
 import android.content.SharedPreferences;		 import android.content.SharedPreferences;
 import android.os.Bundle;		 import android.os.Bundle;
 import android.support.design.widget.FloatingActionButton;		 import android.support.design.widget.FloatingActionButton;
 @@ -23,6 +24,8 @@ public void onClick(View v) {
                 SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();		                 SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                 editor.putString("username",editText.getText().toString());		                 editor.putString("username",editText.getText().toString());
                 editor.apply();		                 editor.apply();
                 Intent intent = new Intent(SweepActivity.this,WeclomeActivity.class);
                 startActivity(intent);
             }		             }
         });		         });
     }		     }



