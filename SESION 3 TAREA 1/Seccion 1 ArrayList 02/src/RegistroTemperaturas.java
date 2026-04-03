import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RegistroTemperaturas {
    private ArrayList<Double> temperaturas;

    //constructor
    public RegistroTemperaturas(ArrayList<Double> temps){
        this.temperaturas = temps;
    }

    //promedio semanal
    public double calcularPromedio(){
        return temperaturas.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);

    }
    //maxima temperatura
    public double calcularMaximo(){
        return Collections.max(temperaturas);
    }
    //minimo temperatura
    public double calcularMinimo(){
        return Collections.min(temperaturas);
    }
    // contar dias sobre 30 °C
    public long contarDiasSobre30(){
        return temperaturas.stream()
                .filter(t -> t > 30)
                .count();
    }
    // determinar la tendencia
    public String determinarTendencia(){
        int subidas = 0,bajadas = 0;
        for (int i = 1; i < temperaturas.size(); i++){
            if (temperaturas.get(i) > temperaturas.get(i-1)) subidas++;
            else if (temperaturas.get(i) < temperaturas.get(i-1)) bajadas++;
            }
        if (subidas>bajadas)return "tendencia: subiendo";
        else if (bajadas>subidas) return "tendencia: bajando";
        else  return "tendecia: estable";
        }
        //metodo main para probrar
        public static void main(String[] args) {
            ArrayList<Double> temps = new ArrayList<>(Arrays.asList(28.5, 31.2, 29.8, 33.1, 30.5, 27.9, 30.5));
            RegistroTemperaturas registro = new RegistroTemperaturas(temps);

            System.out.println("PROMEDIO:"+ registro.calcularPromedio());
            System.out.println("MAXIMO:"+ registro.calcularMaximo());
            System.out.println("MINIMO:"+ registro.calcularMinimo());
            System.out.println("DIAS SOBRE 30°C:"+ registro.contarDiasSobre30());
            System.out.println(registro.determinarTendencia());
        }

    }




