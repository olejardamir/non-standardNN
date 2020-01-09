public class XORProof {

    public static void main(String[] args){

        String[] xorData = {"0,0,1","0,1,0","1,0,1","1,1,0"};

        Neuron neuron = new Neuron();

            double err = 0;
            neuron.setNanotime(-217161972180233l);
            neuron.setAddend(-41L);
            neuron.setMask(-8314819682560L);
            neuron.setMultiplier(15438383123L);


            for (String xor : xorData) {
                String[] sp = xor.split(",");
                double x = Double.parseDouble(sp[0]);
                double y = Double.parseDouble(sp[1]);
                double z = Double.parseDouble(sp[2]);

                double r = neuron.getAssymetricSignal2D(x,y);
                r = Math.round(neuron.getAssymetricSignal3D(x,y,r));

                err = err + Math.abs(r - z);
            }

            System.out.println(err);



    }

}
