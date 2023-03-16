/*
 * 
  Haz un programa con un menú que permita gestionar un parking. Se sigue
el video de esta tarea para su realización. 
Modifico el método salida para que actualice las plazas ocupadas
 */
package rprt09;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Rprt09e10 {
      static ArrayList <Coche> parking;
      static final int  PLAZAS=100;
      static Scanner teclado;
    public static void main(String[] args) {
        parking= new ArrayList<>();
        teclado=new Scanner(System.in);
        
       boolean salir=false; int opcion;
       do{
           System.out.println("1.Entrada \n2)Salida \n3.Lista \n4.Salir ");
           opcion=Integer.parseInt(teclado.nextLine());
           switch(opcion){
               case 1: entrada(); break;
               case 2: salida(); break;
               case 3: lista(); break;
               case 4: salir=true; break;
               default: System.out.println("Opción no válida");
           }
       }while(!salir);
      
       
    }
    static void entrada(){
        
        if(parking.size()<PLAZAS){
             System.out.println("Indica la matrícula");
             String matricula= teclado.nextLine();
            System.out.println("indica el tipo de vehículo");
            System.out.println("1.coche \n2.furgoneta \n3.autobús");
            int tipo= Integer.parseInt(teclado.nextLine());
            switch(tipo){
                case 1: 
                    parking.add(new Coche(matricula));
                    break;
                case 2: 
                    System.out.println("Indica los metros");
                    int metros= Integer.parseInt(teclado.nextLine());
                    parking.add(new Furgoneta(matricula,metros));
                    break;
                case 3:
                    System.out.println("Indica los asientos");
                    int asientos= Integer.parseInt(teclado.nextLine());
                  parking.add(new Autobus(matricula,asientos));
                  break;
                default: System.out.println("Vehículo no permitido en este parkin");
                break;
                }
            System.out.println("Coche estacionado en el parking");
        }
        else System.out.println("Parking lleno");
    }
    
    static void salida(){
        System.out.println("Indica la matrícula");
        String matricula= teclado.nextLine();
       int pos=-1;
        //int pos= parking.indexOf(new Coche(matricula));
        for(Coche c:parking){
            if(c.matricula.contains(matricula)){
                pos=parking.indexOf(c);
                
                
            }
        }
        if(pos!=-1){
            int imp=parking.get(pos).salida();
        System.out.println("Total a pagar "+imp);
        //eliminamos el coche desaparcado
        parking.remove(parking.get(pos));
            
        }else System.out.println("Matricula incorrecta");
            
        
        
    }
    static void lista(){
        for (Coche c:parking){
            System.out.println(c);
        }
        System.out.println("Plazas ocupadas"+ parking.size());
    }
   

   
    
}


class Coche{
    String matricula;
    LocalDateTime fechaEntrada;
    LocalDateTime fechaSalida;
    static final int PRECIOMINUTO=4;
    
    Coche(String matricula){
        this.matricula=matricula;
        fechaEntrada=LocalDateTime.now();
    }
    
    public int salida(){
        fechaSalida=LocalDateTime.now();
        long tiempo=ChronoUnit.SECONDS.between(fechaEntrada, fechaSalida);
        return (int)tiempo*PRECIOMINUTO;
    }

   

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coche other = (Coche) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coche{ matricula=" + matricula + ", fechaEntrada=" + fechaEntrada +" }";
    }
    

}

class Furgoneta extends Coche{
    int longitud;
    static final int COSTEMETRO = 20;
    Furgoneta(String matricula, int longitud){
         super(matricula);
         this.longitud=longitud;
    }
    
   @Override
   public int salida(){
       int importe= super.salida();
       importe+= longitud*COSTEMETRO;
       return importe;
   }
   
    
}


class Autobus extends Coche{
    int asientos;
    static final int COSTEASIENTO=25;
    Autobus(String matricula, int asientos){
        super(matricula);
        this.asientos=asientos;
    }
    @Override
    public int salida(){
        int importe= super.salida();
        importe+=asientos*COSTEASIENTO;
        return importe;
    }
    
}
