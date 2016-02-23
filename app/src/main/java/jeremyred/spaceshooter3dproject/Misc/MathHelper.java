package jeremyred.spaceshooter3dproject.Misc;

import android.opengl.Matrix;

/**
 * Created by jeremy on 1/27/2016.
 */
public  class MathHelper {

    public static float[] getRotationViewMatrix(float[] lookAt,float[] lookFrom,
                                                float rotX,float rotY,float rotZ)
    {
        float[] rotation = new float[16];
        float[] rotationX = new float[16];
        float[] rotationY = new float[16];
        float[] rotationZ = new float[16];
        float[] newLookFrom = new float[4];
        float[] view = new float[16];
        Matrix.setIdentityM(rotation, 0);

        //rotate rotation matrix
        Matrix.setRotateM(rotationX,0,rotX,1,0,0);
        Matrix.setRotateM(rotationY, 0, rotY, 0, 1, 0);
        Matrix.setRotateM(rotationZ, 0, rotZ, 0, 0, 1);

        //put rotaions to gether
        Matrix.multiplyMM(rotation,0,rotationX,0,rotationY,0);
        Matrix.multiplyMM(rotation,0,rotation,0,rotationZ,0);

        Matrix.multiplyMV(newLookFrom, 0, rotation, 0, lookFrom, 0);

        newLookFrom[0] += lookAt[0];
        newLookFrom[1] += lookAt[1];
        newLookFrom[2] += lookAt[2];


        Matrix.setLookAtM(view,0,newLookFrom[0],newLookFrom[1],newLookFrom[2],lookAt[0],lookAt[1],lookAt[2],0,0,1);
        return  view;


    }

}
