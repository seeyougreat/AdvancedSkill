package tn.uu.advancedskill;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

/*
 * 总结：
 * 直接在xml中使用的优先
 * 在xml中 style="@style/style_viewStyle"次之
 * 自定义SelfView中使用的defStyleAttr再次之
 * 最后是Theme中的赋值的属性
 */
import androidx.annotation.Nullable;

public class SelfView extends androidx.appcompat.widget.AppCompatTextView {
    public SelfView(Context context) {
        this(context, null);
    }

    public SelfView(Context context, @Nullable AttributeSet attrs) {
        // 使用定义的属性
        this(context, attrs, R.attr.attr_defStyle);
    }

    public SelfView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.SelfView,defStyleAttr,0);
        String text1 = typedArray.getString(R.styleable.SelfView_text1);
        String text2 = typedArray.getString(R.styleable.SelfView_text2);
        String text3 = typedArray.getString(R.styleable.SelfView_text3);
        String text4 = typedArray.getString(R.styleable.SelfView_text4);
        typedArray.recycle();

        //优先级输出
//        Log.e("xxxxx--", "SelfView: " +text1 );
//        Log.e("xxxxx--", "SelfView: " +text2 );
//        Log.e("xxxxx--", "SelfView: " +text3 );
//        Log.e("xxxxx--", "SelfView: " +text4 );

        /*
         *  SelfView: xml text1
         *  SelfView: text2 style_viewStyle
         *  SelfView: text3 style_attr_defStyleAttr
         *  SelfView: text4 theme
         */
    }
}
