# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*



#-keep public class * extends android.app.Activity                               # 保持哪些类不被混淆
#-keep public class * extends android.app.Application                            # 保持哪些类不被混淆
#-keep public class * extends android.app.Service                                # 保持哪些类不被混淆
#-keep public class * extends android.content.BroadcastReceiver                  # 保持哪些类不被混淆
#-keep public class * extends android.content.ContentProvider                    # 保持哪些类不被混淆
#-keep public class * extends android.app.backup.BackupAgentHelper               # 保持哪些类不被混淆
#-keep public class * extends android.preference.Preference                      # 保持哪些类不被混淆


-keepclasseswithmembernames class * {                                           # 保持 native 方法不被混淆
    native <methods>;
}

#-keepclasseswithmembers class * {                                               # 保持自定义控件类不被混淆
#    public <init>(android.content.Context, android.util.AttributeSet);
#}
#
#-keepclasseswithmembers class * {
#    public <init>(android.content.Context, android.util.AttributeSet, int);     # 保持自定义控件类不被混淆
#}
#
#-keepclassmembers class * extends android.app.Activity {                        # 保持自定义控件类不被混淆
#   public void *(android.view.View);
#}

#-keepclassmembers enum * {                                                      # 保持枚举 enum 类不被混淆
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}

-keep class * implements android.os.Parcelable {                                # 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}


##不混淆资源类
#-keepclassmembers class **.R$* {
#    public static <fields>;
#}


#把混淆类中的方法名也混淆了
-useuniqueclassmembernames

#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

#将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SourceFile
#保留行号
-keepattributes SourceFile,LineNumberTable
#保持泛型
-keepattributes Signature

#保持所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 由于JNI需要调用该属性，指定类的属性和方法不被混淆
#-keep class com.transcom.baselibrary.utils.FFT2 {
#    private long mPtr;
#}

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.app.Fragment

# =====================================================================
#-keep class com.siso.edu.instrument.datatemplate.**{*;}
-keep class com.siso.edu.instrument.datatemplate.savedata.model.**{*;}


# =====================================================================

# =====================================================================
# Apache POI
# https://github.com/centic9/poi-on-android/blob/master/poitest/proguard-rules.pro

-keep class org.apache.poi.** { *; }
-keep class org.apache.xmlbeans.** { *; }
-keep class schemaorg_apache_xmlbeans.system.*.TypeSystemHolder { public final static *** typeSystem; }
#-keep class com.fasterxml.aalto.stax.InputFactoryImpl
#-keep class com.fasterxml.aalto.stax.OutputFactoryImpl
#-keep class com.fasterxml.aalto.stax.EventFactoryImpl
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CommentsDocument { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBooleanProperty { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookView { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorders { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellXfs { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCommentList { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFills { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFonts { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontName { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontScheme { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontSize { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmts { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetData { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetView { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSst { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookPr { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType$Enum { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType$Enum { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CommentsDocumentImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBooleanPropertyImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBookViewImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBookViewsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBorderImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBordersImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBorderPrImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCellImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCellAlignmentImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCellFormulaImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCellStyleXfsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCellXfsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTColorImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTColImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTColsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCommentImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCommentsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCommentListImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTDrawingImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFillImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFillsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontNameImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontSchemeImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontSizeImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTIntPropertyImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTLegacyDrawingImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTNumFmtsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPatternFillImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPageMarginsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPaneImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRowImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSelectionImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetDataImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetDimensionImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetFormatPrImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetViewImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetViewsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetsImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSstImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTStylesheetImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorkbookImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorkbookPrImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTXfImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.SstDocumentImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.StyleSheetDocumentImpl { *; }
#-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.STXstringImpl { *; }
#-keep class org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.CTPropertiesImpl { *; }
#-keep class org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.PropertiesDocumentImpl { *; }
#-keep class org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl.CTPropertiesImpl { *; }
#-keep class org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl.PropertiesDocumentImpl { *; }
#-keep class org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl { *; }
#-keep class org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTMarkerImpl { *; }
#-keep class com.microsoft.schemas.office.office.impl.CTIdMapImpl { *; }
#-keep class com.microsoft.schemas.office.office.impl.CTShapeLayoutImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTShadowImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTFillImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTPathImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTShapeImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTShapetypeImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTStrokeImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTTextboxImpl { *; }
#-keep class com.microsoft.schemas.office.excel.impl.CTClientDataImpl { *; }
#-keep class com.microsoft.schemas.office.excel.impl.STTrueFalseBlankImpl { *; }
-keep class javax.xml.stream.**{*;}
# =====================================================================

-keep class jxl.**{*;}

#-keep class common.log.SimpleLogger{*;}

# 保持测试相关的代码
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

# 注意事项：
#
# ① jni方法不可混淆，方法名需与native方法保持一致；
# ② 反射用到的类不混淆，否则反射可能出问题；
# ③ 四大组件、Application子类、Framework层下的类、自定义的View默认不会被混淆，无需另外配置；
# ④ WebView的JS调用接口方法不可混淆；
# ⑤ 注解相关的类不混淆；
# ⑥ GSON、Fastjson等解析的Bean数据类不可混淆；
# ⑦ 枚举enum类中的values和valuesof这两个方法不可混淆(反射调用)；
# ⑧ 继承Parceable和Serializable等可序列化的类不可混淆；
# ⑨ 第三方库或SDK，请参考第三方提供的混淆规则，没提供的话，建议第三方包全部不混淆


##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.bean.** {*;}

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

##---------------End: proguard configuration for Gson  ----------

# baidu map
-keep class com.baidu.**{*;}
-keep class vi.com.gdi.bgl.android.java.**{*;}