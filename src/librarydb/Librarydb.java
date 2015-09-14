/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarydb;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sergio
 */
public class Librarydb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //connect c1=new connect();
        //book b1=new book();
        //lend l1=new lend();
        String name=null;
        String author=null;
        String year=null;
        String stock=null;
        String code=null;
        String area=null;
        String area2=null;
        String doc=null;
        String vall=null;
        String idd=null;
        int flag=0,stockk,flag2=0,val=0,id=0,ff=0;
        Connection con;
        Scanner kb= new Scanner(System.in);
        Statement state;
        String user="sergiogomeza9210";
        String password="sasdas31428";
        //String user="root";
        //String password="1234";
        char op1='0',op2='0',op3='0',op4='0';
        
        try{
            System.out.print("Intentando conectar a la base de datos...");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://db4free.net/contactos8sgza",user,password);
            //con = DriverManager.getConnection("jdbc:mysql://localhost/contactos8sgza",user,password);
            System.out.print("conexion exitosa...\n");
            System.out.print("-------------------------------------------\n");
            state = con.createStatement();
            System.out.print("Bienvenido a la aplicacion de la biblioteca\n");
            System.out.print("-------------------------------------------\n");
            //ResultSet resultado = state.executeQuery("SELECT * FROM `Libros`");
            ResultSet resultado = state.executeQuery("SELECT * FROM `libros`");
            while(op1=='0'){
                //resultado = state.executeQuery("SELECT * FROM `Libros`");
                /*resultado = state.executeQuery("SELECT * FROM `libros`");
                while(resultado.next()){
                    System.out.println(resultado.getString("nombre")+"\t\t"+resultado.getString("autor")+"\t"+resultado.getString("year")+"\t"+resultado.getString("code")+"\t"+resultado.getString("cantidad")+"\t"+resultado.getString("area"));
                }*/
                System.out.print("-------------------------------------------\n");
                System.out.print("Ingrese la opcion que desea\n1.Gestion base de datos\n2.Gestion de prestamos\n3.Salir\n(1/2/3): ");
                op1=kb.next().charAt(0);
                kb.nextLine();
                System.out.print("-------------------------------------------\n");
                switch(op1){
                    case '1':
                        while(op2=='0'){
                            System.out.print("Que desea hacer?\n1.Ingresar Libro\n2.Actualizar Libro\n3.Eliminar Libro\n4.Buscar Libro\n5.Volver al menú anterior\n(1/2/3/4/5):");
                            op2=kb.next().charAt(0);
                            kb.nextLine();
                            System.out.print("-------------------------------------------\n");
                            switch(op2){
                                case '1':
                                    System.out.println("Ingrese el nombre del libro: ");
                                    name=kb.nextLine();
                                    resultado = state.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+name+"'");
                                    if(resultado.next()){
                                        System.out.print("El libro ya está en la base de datos\n");
                                        System.out.print("-------------------------------------------\n");
                                    }else{
                                        System.out.println("Ingrese el nombre del autor: ");
                                        author=kb.nextLine();
                                        System.out.println("Ingrese el año del libro: ");
                                        year=kb.nextLine();
                                        while(flag2==0){
                                            flag2=1;
                                            System.out.println("Ingrese la cantidad de libros: ");
                                            stock=kb.nextLine();
                                            try{
                                                stockk=Integer.parseInt(stock);
                                            }catch(NumberFormatException e){
                                                flag2=0;
                                                System.out.print("-------------------------------------------\n");
                                                System.out.println("Este valor debe ser un numero entero!!!\n");
                                                System.out.print("-------------------------------------------\n");
                                            }
                                        }
                                        System.out.println("Ingrese el codigo del libro: ");
                                        code=kb.nextLine();
                                        while(flag==0){
                                            System.out.println("Ingrese el area del libro(quimica/fisica/tecnologia/calculo/programacion): ");
                                            area2=kb.nextLine();
                                            switch(area2){
                                                case "quimica":
                                                case "fisica":
                                                case "tecnologia":
                                                case "calculo":
                                                case "programacion":
                                                    flag=1;
                                                    break;
                                                default:
                                                    System.out.print("area incorrecta\ndebe ser de quimica,fisica,tecnologia,calculo o programacion\n");
                                            }
                                            area=area2;
                                        }
                                        //state.executeUpdate("INSERT INTO `contactos8sgza`.`Libros` (`id`, `nombre`, `autor`, `year`, `cantidad`, `code`, `area`) VALUES (NULL, '"+name+"', '"+author+"', '"+year+"', '"+stock+"', '"+code+"', '"+area+"')");
                                        state.executeUpdate("INSERT INTO `contactos8sgza`.`libros` (`nombre`, `autor`, `year`, `cantidad`, `code`, `area`) VALUES ('"+name+"', '"+author+"', '"+year+"', '"+stock+"', '"+code+"', '"+area+"')");
                                    }
                                    flag=0;
                                    flag2=0;
                                    op2='0';
                                    System.out.print("-------------------------------------------\n");
                                    break;
                                case '2':
                                    System.out.println("Ingrese el nombre del libro que desea actualizar: ");
                                    name=kb.nextLine();
                                    resultado = state.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+name+"'");
                                    if(resultado.next()){
                                        System.out.println("1.autor\n2.año\n3.cantidad\n4.codigo\n5.area\nque desea actualizar?: ");
                                        op4=kb.nextLine().charAt(0);
                                        //kb.nextLine();
                                        System.out.print("-------------------------------------------\n");
                                        switch(op4){
                                            case'1':
                                                System.out.println("Ingrese el nuevo nombre del autor: ");
                                                author=kb.nextLine();
                                                state.executeUpdate("UPDATE `contactos8sgza`.`libros` SET `autor` = '"+author+"' WHERE `libros`.`nombre` = '"+name+"'");
                                                break;
                                            case'2':
                                                System.out.println("Ingrese el nuevo año del libro: ");
                                                year=kb.nextLine();
                                                state.executeUpdate("UPDATE `contactos8sgza`.`libros` SET `year` = '"+year+"' WHERE `libros`.`nombre` = '"+name+"'");
                                                break;
                                            case'3':
                                                while(flag2==0){
                                                    flag2=1;
                                                    System.out.println("Ingrese la nueva cantidad de libros: ");
                                                    stock=kb.nextLine();
                                                    try{
                                                        stockk=Integer.parseInt(stock);
                                                    }catch(NumberFormatException e){
                                                        flag2=0;
                                                        System.out.print("-------------------------------------------\n");
                                                        System.out.println("Este valor debe ser un numero entero!!!\n");
                                                        System.out.print("-------------------------------------------\n");
                                                    }
                                                }
                                                state.executeUpdate("UPDATE `contactos8sgza`.`libros` SET `cantidad` = '"+stock+"' WHERE `libros`.`nombre` = '"+name+"'");
                                                break;
                                            case'4':
                                                System.out.println("Ingrese el nuevo codigo del libro: ");
                                                code=kb.nextLine();
                                                state.executeUpdate("UPDATE `contactos8sgza`.`libros` SET `code` = '"+code+"' WHERE `libros`.`nombre` = '"+name+"'");
                                                break;
                                            case'5':
                                                while(flag==0){
                                                    System.out.println("Ingrese el area del libro(quimica/fisica/tecnologia/calculo/programacion): ");
                                                    area2=kb.nextLine();
                                                    switch(area2){
                                                        case "quimica":
                                                        case "fisica":
                                                        case "tecnologia":
                                                        case "calculo":
                                                        case "programacion":
                                                            flag=1;
                                                            break;
                                                        default:
                                                            System.out.print("area incorrecta\ndebe ser de quimica,fisica,tecnologia,calculo o programacion\n");
                                                    }
                                                    area=area2;
                                                }
                                                state.executeUpdate("UPDATE `contactos8sgza`.`libros` SET `area` = '"+area+"' WHERE `libros`.`nombre` = '"+name+"'");
                                                break;
                                            default:
                                                
                                        }
                                        
                                    }else{
                                        System.out.print("Ese libro no está en la base de datos\n");
                                        System.out.print("-------------------------------------------\n");
                                    }
                                    flag=0;
                                    flag2=0;
                                    op2='0';
                                    break;
                                case '3':
                                    System.out.println("Ingrese el nombre del libro que desea eliminar: ");
                                    name=kb.nextLine();
                                    resultado = state.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+name+"'");
                                    if(resultado.next()){
                                        state.executeUpdate("DELETE FROM `libros` WHERE `nombre` LIKE '"+name+"'");
                                    }else{
                                        System.out.print("Ese libro no está en la base de datos\n");
                                        System.out.print("-------------------------------------------\n");
                                    }
                                    op2='0';
                                    break;
                                case '4':
                                    System.out.println("digite el nombre del libro que busca: ");
                                    name=kb.nextLine();
                                    System.out.print("-------------------------------------------\n");
                                    resultado = state.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+name+"'");
                                    if(resultado.next()){
                                        System.out.println("libro: "+resultado.getString("nombre")+"\nautor: "+resultado.getString("autor")
                                        +"\naño: "+resultado.getString("year")+"\ncantidad: "+resultado.getString("cantidad")
                                        +"\ncodigo: "+resultado.getString("code")+"\narea: "+resultado.getString("area"));
                                    }else{
                                        System.out.print("Ese libro no está en la base de datos\n");
                                        System.out.print("-------------------------------------------\n");
                                    }
                                    System.out.print("-------------------------------------------\n");
                                    op2='0';
                                    break;
                                case '5':
                                    break;
                                default:
                                    System.out.print("opcion incorrecta\n");
                                    System.out.print("-------------------------------------------\n");
                                    op2='0';
                            }
                        }
                        op2='0';
                        op1='0';
                        break;
                    case '2':
                        while(op3=='0'){
                            System.out.print("1.prestar libro\n2.devolver libro\n3.libros prestados\n4.volver al menu anterior\n(1/2/3/4): ");
                            op3=kb.next().charAt(0);
                            kb.nextLine();
                            System.out.print("-------------------------------------------\n");
                            switch(op3){
                                case'1':
                                    System.out.println("Ingrese el numero de documento de quien presta el libro: ");
                                    doc=kb.nextLine();
                                    System.out.println("Ingrese el nombre del libro: ");
                                    name=kb.nextLine();
                                    resultado = state.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+name+"'");
                                    if(resultado.next()){
                                        vall=resultado.getString("cantidad");
                                        val=Integer.parseInt(vall);
                                        if(val>0){
                                            val--;
                                            state.executeUpdate("INSERT INTO `contactos8sgza`.`prestamo` (`id`, `document`, `book`) VALUES (NULL, '"+doc+"', '"+name+"')");
                                            state.executeUpdate("UPDATE `contactos8sgza`.`libros` SET `cantidad` = '"+val+"' WHERE `libros`.`nombre` = '"+name+"'");
                                        }else{
                                            System.out.print("No quedan ejemplares de ese libro\n");
                                            System.out.print("-------------------------------------------\n");
                                        }
                                    }else{
                                        System.out.print("Ese libro no está en la base de datos\n");
                                        System.out.print("-------------------------------------------\n");
                                    }
                                    op3='0';
                                    op2='0';
                                    System.out.print("-------------------------------------------\n");
                                    break;
                                case'2':
                                    System.out.print("Ingrese el nombre del libro que va a devolver: ");
                                    name=kb.nextLine();
                                    System.out.println("Ingrese el numero de documento de quien lo prestó: ");
                                    doc=kb.nextLine();
                                    resultado = state.executeQuery("SELECT * FROM `prestamo` WHERE `document` LIKE '"+doc+"' AND `book` LIKE '"+name+"'");
                                    if(resultado.next()){
                                        idd=resultado.getString("id");
                                        //System.out.print(resultado.getString("book")+" prestado a: "+resultado.getString("document")+"\n");
                                        //System.out.println("Ingrese el numero de documento de quien presta el libro: ");
                                        //doc=kb.nextLine();
                                        state.executeUpdate("DELETE FROM `prestamo` WHERE `id` LIKE '"+idd+"'");
                                        resultado = state.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+name+"'");
                                        if(resultado.next()){
                                            vall=resultado.getString("cantidad");
                                            val=Integer.parseInt(vall);
                                            val++;
                                            state.executeUpdate("UPDATE `contactos8sgza`.`libros` SET `cantidad` = '"+val+"' WHERE `libros`.`nombre` = '"+name+"'");
                                        }
                                        
                                        //resultado = state.executeQuery("SELECT * FROM `prestamo` WHERE `nombre` LIKE '"+name+"'");
                                    }else{
                                        System.out.print("Esa persona no ha prestado el libro\n");
                                        System.out.print("-------------------------------------------\n");
                                    }
                                    System.out.print("-------------------------------------------\n");
                                    op3='0';
                                    break;
                                case'3':
                                    resultado = state.executeQuery("SELECT * FROM `prestamo`");
                                    while(resultado.next()){
                                        ff++;
                                        System.out.println(resultado.getString("book")+"\t\tprestado a: "+resultado.getString("document"));
                                    }
                                    if(ff==0){
                                        System.out.print("No hay libros prestados!!\n");
                                        System.out.print("-------------------------------------------\n");
                                    }
                                    System.out.print("-------------------------------------------\n");
                                    ff=0;
                                    op3='0';
                                    break;
                                case'4':
                                    break;
                                default:
                                    op3='0';
                            }
                        }
                        op3='0';
                        op1='0';
                        break;
                    case '3':
                        System.out.print("Hasta Luego!!!\n");
                        System.out.print("-------------------------------------------\n");
                        break;
                    default:
                        System.out.print("opcion incorrecta\n");
                        System.out.print("-------------------------------------------\n");
                        op1='0';
                }
            }
        }catch (SQLException ex) {
            System.out.print("Error de mysql\n");
        }catch (Exception e){
            System.out.print("Se ha encontrado un error que es: "+e.getMessage()+'\n');
        }
        
        
    }
    
}
