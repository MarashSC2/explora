package explora.de.exploramaterial.NavigationDraw;

import android.graphics.drawable.Icon;

/**
 * Created by yannik on 01/06/2016.
 */
public class NavDrawerItem {
        private boolean showNotify;
        private String title;
        private int icon;


        public NavDrawerItem() {

        }

        public NavDrawerItem(boolean showNotify, String title,int icon) {
            this.showNotify = showNotify;
            this.title = title;
            this.icon=icon;
        }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isShowNotify() {
            return showNotify;
        }

        public void setShowNotify(boolean showNotify) {
            this.showNotify = showNotify;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
}
