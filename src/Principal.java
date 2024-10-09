import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args){
        Scanner lectura = new Scanner(System.in);

        int opcion = 0;


        String menu = """
                *************************************************
                Sea bienvenido al Conversor de Moneda
                *************************************************
                Qué desea convertir?
                1. Dólar a Peso Argentino
                2. Peso Argentino a Dólar
                3. Dolar a Real Brasileño
                4. Real Brasileño a Dólar
                5. Dolar a Peso Colombiano
                6. Peso Colombiano a Dólar
                7. Dólar a Peso Chileno
                8. Peso Chileno a Dólar
                9. Salir
                
                Elija una opción válida:
                *************************************************
                """;

        while (opcion != 9) {
            String baseCode = "";
            String changeCode = "";
            Double monto = 0.0;
            Double conversion = 0.0;

            System.out.println(menu);
            try {
                opcion = lectura.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un numero entero");
                lectura.next();
            }
            switch (opcion){
                case 1:
                    baseCode = "USD";
                    changeCode = "ARS";
                    break;
                case 2:
                    baseCode = "ARS";
                    changeCode = "USD";
                    break;
                case 3:
                    baseCode = "USD";
                    changeCode = "BRL";
                    break;
                case 4:
                    baseCode = "BRL";
                    changeCode = "USD";
                    break;
                case 5:
                    baseCode = "USD";
                    changeCode = "COP";
                    break;
                case 6:
                    baseCode = "COP";
                    changeCode = "USD";
                    break;
                case 7:
                    baseCode = "USD";
                    changeCode = "CLP";
                    break;
                case 8:
                    baseCode = "CLP";
                    changeCode = "USD";
                    break;
                case 9:
                    System.out.println("Gracias por usar nuestro conversor.");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
            if (opcion == 9) break;
            if (opcion < 9 && opcion >0){
                try {
                    ConsultaCambio cambio = new ConsultaCambio();
                    String mensajeMonto = "Ingrese la cantidad de ["+ baseCode +"] que desea convertir a ["+ changeCode +"]:";
                    System.out.println(mensajeMonto);
                    monto = lectura.nextDouble();
                    Moneda moneda = cambio.buscaMoneda(baseCode);
                    if (moneda.getResult().equals("success")){
                        conversion = moneda.CalcularConversion(changeCode,monto);

                        DecimalFormat df = new DecimalFormat("#.00");
                        String conversionFormateada = df.format(conversion);

                        String mensajeResultado = "Los "+monto+" ["+baseCode+"] equivalen a "+conversionFormateada+" ["+changeCode+"].";
                        System.out.println(mensajeResultado);
                    }
                    else {
                        System.out.println("No se pudo establecer conexion con la API");
                    }

                } catch (InputMismatchException e){
                    System.out.println("Debe ingresar un numero valido");
                    lectura.next();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
