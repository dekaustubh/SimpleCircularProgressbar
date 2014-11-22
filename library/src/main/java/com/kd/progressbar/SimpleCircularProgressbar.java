package com.kd.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Kaustubh on 11/17/2014.
 */


/*
CircularProgressbar extends view, to create a custom CircularProgressbar
*/
// *** I am very bad at documentation ***

public class SimpleCircularProgressbar extends View {

    private Context mContext;

    // constants for telling rotation of a progress
    public static final int CLOCKWISE = 0;
    public static final int ANTICLOCKWISE = 1;

    // constants to tell from which position we want to start the progress
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;

    // coloring the traversal
    private float mTraverse = 0.0f;

    // Save instance of canvas to draw anywhere in the file
    private Canvas mCanvas;

    // Current progress of progressbar
    private float mCurrentProgress;

    // Maximus progress of progressbar
    private float mMaxProgress;

    // Thickness of a progressbar
    private int mThickness;

    // RectF ecnlosing the circular progressbar
    private final RectF mOuterCircleBounds = new RectF();
    private final RectF mUnprogressedBounds = new RectF();

    // color of progressbar, which has traversed
    private int mProgressbarColor;

    // default (normal) color of progressbar, which has not made any progress
    private int mProgressbarBackgroundColor;

    // integer value indicating at which position the progress should start
    // 0 : left
    // 1 : top
    // 2 : right
    // 3 : bottom
    private int mStartAt;

    // rotation value
    private int mRotation;

    // used to paint the progress of progressbar
    private Paint mProgressbarPaint;

    // used to paint un-progress part of progressbar
    private Paint mUnProgressbarPaint;

    public SimpleCircularProgressbar(final Context context) {
        this(context, null);
    }

    public SimpleCircularProgressbar(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.circularProgressbarStyle);
    }

    public SimpleCircularProgressbar(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        final TypedArray attributes = mContext.obtainStyledAttributes(attrs, R.styleable.SimpleCircularProgressbar , defStyleAttr, 0);

        if(attributes != null){
            try {
                mMaxProgress = attributes.getFloat(R.styleable.SimpleCircularProgressbar_maxProgress, 100.0f);
                mCurrentProgress = attributes.getFloat(R.styleable.SimpleCircularProgressbar_progress, 0.0f);
                mStartAt = attributes.getInteger(R.styleable.SimpleCircularProgressbar_startAt, 1);     // default will be top
                mRotation = attributes.getInteger(R.styleable.SimpleCircularProgressbar_rotation, 0);
                mProgressbarColor = attributes.getColor(R.styleable.SimpleCircularProgressbar_progressColor, Color.BLACK);
                mProgressbarBackgroundColor = attributes.getColor(R.styleable.SimpleCircularProgressbar_backgroundColor, Color.GRAY);
                mThickness = attributes.getInteger(R.styleable.SimpleCircularProgressbar_thickness, 8);
            }
            finally {
                attributes.recycle();
            }

            init();
        }
    }

    /**
        This code block does main initialization of the view related stuff
     */
    private void init(){
        mProgressbarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressbarPaint.setColor(mProgressbarColor);
        mUnProgressbarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mUnProgressbarPaint.setColor(mProgressbarBackgroundColor);
        calculateProgress();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defH = getDefaultSize(getSuggestedMinimumHeight()+getPaddingBottom()+getPaddingTop(),
                heightMeasureSpec);
        int defW = getDefaultSize(getSuggestedMinimumWidth()+getPaddingLeft()+getPaddingRight(),
                widthMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if( (widthMode == MeasureSpec.UNSPECIFIED || widthMode == MeasureSpec.AT_MOST)){
            defW = 75;
        }
        if( (heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST)){
            defH = 75;
        }
        setMeasuredDimension(defW, defH);
        if(defH < defW) {
            // height is small
            final float pHalfH = defH/2f;
            final float pHalfW = defW/2f;
            defH = defH - 20;
            final float halfH = defH/2f;
            mOuterCircleBounds.set(pHalfW-halfH, pHalfH-halfH, pHalfW+halfH, pHalfH+halfH);
            mUnprogressedBounds.set(pHalfW-halfH, pHalfH-halfH, pHalfW+halfH, pHalfH+halfH);
        }
        else {
            // width is small
            final float pHalfH = defH/2f;
            final float pHalfW = defW/2f;
            defW = defW - 20;
            final float halfW = defW/2f;
            mOuterCircleBounds.set(pHalfW-halfW , pHalfH-halfW, pHalfW+halfW, pHalfH+halfW);
            mUnprogressedBounds.set(pHalfW-halfW , pHalfH-halfW, pHalfW+halfW, pHalfH+halfW);
        }
        mProgressbarPaint.setStrokeWidth(mThickness);
        mProgressbarPaint.setStyle(Paint.Style.STROKE);

        mUnProgressbarPaint.setStrokeWidth(mThickness);
        mUnProgressbarPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        mCanvas = canvas;

        switch (mRotation){
            case 0:
                // clockwise
                printClockwise(canvas);
                break;

            case 1:
                // anti clockwise
                printAntiClockwise(canvas);
                break;
        }
    }

    private void printClockwise(final Canvas canvas){
        switch (mStartAt) {
            case 0:
                // left
                canvas.drawArc(mOuterCircleBounds, 180, mTraverse, false, mProgressbarPaint);
                canvas.drawArc(mUnprogressedBounds, 180, -((360-mTraverse)), false, mUnProgressbarPaint);
                break;

            case 1:
                // top
                canvas.drawArc(mOuterCircleBounds, 270, mTraverse, false, mProgressbarPaint);
                canvas.drawArc(mUnprogressedBounds, 270, -((360-mTraverse)), false, mUnProgressbarPaint);
                break;

            case 2:
                // right
                canvas.drawArc(mOuterCircleBounds, 0, mTraverse, false, mProgressbarPaint);
                canvas.drawArc(mUnprogressedBounds, 0, -((360-mTraverse)), false, mUnProgressbarPaint);
                break;

            case 3:
                // bottom
                canvas.drawArc(mOuterCircleBounds, 90, mTraverse, false, mProgressbarPaint);
                canvas.drawArc(mUnprogressedBounds, 90, -((360-mTraverse)), false, mUnProgressbarPaint);
                break;
        }
    }

    private void printAntiClockwise(final Canvas canvas){
        switch (mStartAt) {
            case 0:
                // left
                canvas.drawArc(mOuterCircleBounds, 180, 360, false, mProgressbarPaint);
                canvas.drawArc(mUnprogressedBounds, 180, (360-mTraverse), false, mUnProgressbarPaint);
                break;

            case 1:
                // top
                canvas.drawArc(mOuterCircleBounds, 270, 360, false, mProgressbarPaint);
                canvas.drawArc(mUnprogressedBounds, 270, (360-mTraverse), false, mUnProgressbarPaint);
                break;

            case 2:
                // right
                canvas.drawArc(mOuterCircleBounds, 0, 360, false, mProgressbarPaint);
                canvas.drawArc(mUnprogressedBounds, 0, (360-mTraverse), false, mUnProgressbarPaint);
                break;

            case 3:
                // bottom
                canvas.drawArc(mOuterCircleBounds, 90, 360, false, mProgressbarPaint);
                canvas.drawArc(mUnprogressedBounds, 90, (360-mTraverse), false, mUnProgressbarPaint);
                break;
        }
    }

    /**
     * Sets the passed progress to current progress and
     * updates the Progressbar
     * @param progress current value traced by progressbar
     */
    public void setProgress(final float progress){
        mCurrentProgress = progress;
        calculateProgress();
        invalidate();
    }

    /**
    * Sets the maximum progress for progress bar
    * @param maxProgress maximum value of progress
    * @see : setProgress()
    */
    public void setMaxProgress(final float maxProgress){
        mMaxProgress = maxProgress;
    }

    /**
     * Sets the rotation of progressbar
     * @param rotation 0 for clockwise, 1 for anti-clockwise
     */
    public void setRotation(final int rotation){
        switch (rotation){
            case 1:
                mRotation = rotation;
                break;

            default:
                mRotation = 0;
                break;
        }
    }

    /**
     * Sets the starting point of progress
     * whether it is top, bottom , right or left
     * @param from position from which it progress should start
     */
    public void startFrom(final int from){
        switch(from){
            case 0:
                //left
                mStartAt = 0;
                break;
            case 2:
                // right
                mStartAt = 2;
                break;
            case 3:
                // bottom
                mStartAt = 3;
                break;
            default:
                mStartAt = 1;
                break;

        }
    }

    /**
     * Sets thickness of the ring
     * @param thickness it will specify size of ring
     */
    public void setThickness(final int thickness){
        mThickness = thickness;
    }

    /**
     * Sets color which will be highlighted.
     * It will be actual progress color
     * @param color color which will be color which will be set to progressed bar
     */
    public void setProgressColor(final int color){
        mProgressbarColor = color;
        mProgressbarPaint.setColor(mProgressbarColor);
    }

    /**
     * Sets color which won't be highlighted.
     * It will be actual un-progressed color
     * @param color color which will be set to un-progressed bar
     */
    public void setSecondaryColor(final int color){
        mProgressbarBackgroundColor = color;
        mUnProgressbarPaint.setColor(mProgressbarBackgroundColor);
    }

    /**
     * Calculated perfect progress according to max and current progress
     */
    private void calculateProgress(){
        if(mCurrentProgress >= mMaxProgress){
            mTraverse = 360;
            return;
        }
        mTraverse = (mCurrentProgress*360)/mMaxProgress;
    }
}
