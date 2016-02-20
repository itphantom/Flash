package flash.lucida.com.flash;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.AlertDialog;

public class SettingsActvit extends PreferenceActivity implements Preference.OnPreferenceClickListener {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting);
            Preference pAppVersion = (Preference)findPreference("keyagree");
            pAppVersion.setOnPreferenceClickListener(this);
        }
        @Override
        public boolean onPreferenceClick(Preference preference)
        {
            // 도움말 선택시
            if(preference.getKey().equals("keyagree"))
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("약관 및 취급방침");

                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }

                });

                alert.setMessage("이 어플은 카메라 권한을 가지고 있습니다. \n 이 권한은 플래시를 작동시키기 위한 권한이며 다른의도로는 사용되지 않습니다.");

                alert.show();

            }
            return false;
        }


}
