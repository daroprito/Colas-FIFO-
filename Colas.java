/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David Rodolfo Prieto Torres
 * @author Erika Ñañez Prada
 */
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Colas extends JFrame implements ActionListener {
    
   
     Nodo2 raiz, fondo; //Definicion de dos punteros externos
    static Colas cola1; 
    JButton inserta, listar, eliminar, buscar;
    JTextField datos;
    TextArea texto;
  dibujar can;
    Colas() {
        raiz = null;//En el constructor inicializamos a los dos punteros en null
        fondo = null;
        // Interfaz
        setSize(800, 500);
        setLayout(null);
        
        inserta = new JButton("Insertar");
        inserta.addActionListener(this);
        listar = new JButton("Listar");
        listar.addActionListener(this);
        eliminar = new JButton("Eliminar");
        eliminar.addActionListener(this);
        buscar = new JButton("Buscar");
        buscar.addActionListener(this);
        datos = new JTextField(10);
        texto = new TextArea(180, 180);
        datos.setBounds(10, 10, 40, 30);
        inserta.setBounds(80, 10, 80, 20);
        listar.setBounds(170, 10, 80, 20);
        eliminar.setBounds(260, 10, 80, 20);
        buscar.setBounds(350, 10, 80, 20);
        texto.setBounds(10, 70, 300, 300);
        can=new dibujar();
        can.setBackground(Color.CYAN);
        can.setBounds(301, 70, 400, 400);
        add(inserta);
        add(listar);
        add(eliminar);
        add(buscar);
        add(datos);
        add(texto);
        add(can);
        }
    
    public void actionPerformed(ActionEvent e) {
       //Acciones de los botones
        if (e.getSource() == inserta) {
            try {
                cola1.insertar(Integer.parseInt(datos.getText()));
                datos.setText("");
            } catch (Exception u) {
            }
        }
        if (e.getSource() == listar) {
            cola1.imprimir();
          can.fijar(this);
        }
        if (e.getSource() == eliminar) {
            cola1.extraer();
            cola1.imprimir();
        }
        if (e.getSource() == buscar) {
           String dat = cola1.buscar(); 
            if (dat.length()>0) { 
                texto.append("Numero encontrado en la posicion " + dat + "\n");
            } else {
                texto.append("Numero no encontrado \n");
            }
        }
    }

    boolean vacia() { //Retorna true si la lista no tiene nodos y false en caso contrario
        if (raiz == null) {
            return true;
        } else {
            return false;
        }
    }

    void insertar(int info) {
        Nodo2 nuevo; //Puntero
        nuevo = new Nodo2(); // Creamos el nodo y cargamos los dos campos
        nuevo.info = info; //Llega en el parámetro 
        nuevo.sig = null; //Se insertará al final de la lista, no hay otro después de este.
        if (vacia()) {
            raiz = nuevo;
            fondo = nuevo;
        } else {
            fondo.sig = nuevo; // Enlaza el puntero sig del último nodo con el nodo recién creado
            fondo = nuevo; //El puntero externo fondo debe apuntar al nodo apuntado por nuevo
        }
    }

    int extraer() {
        if (!vacia()) { 
            int informacion = raiz.info; //Si la lista no está vacía guardamos en una variable local la información del primer nodo
            if (raiz == fondo) {//Para saber si hay un solo nodo verificamos si los dos punteros raiz y fondo apuntan a la misma dirección de memoria
                raiz = null;
                fondo = null;
            } else {
                raiz = raiz.sig; //En caso de haber 2 o más nodos debemos avanzar el puntero raiz al siguiente nodo
            }
            return informacion;
        } else {
            texto.append("Cola vacia");
            return Integer.MAX_VALUE;
        }
    }

    String buscar() { // metodo buscar numero dentro de la lista.
        int numero = Integer.parseInt(datos.getText());
        Nodo2 aux = raiz; // nodo auxiliar es igual a la raiz.
        int x = 1; // varible entera x igual a cero como contador.
        String posi=""; //concatenar posiciones
        while (aux != null) { // mientras que el auxiliar sea diferente de vacio...
            if (aux.info == numero) { // si auxiliar numero es igual al numero ingresado.
                if(posi.length()==0) 
                    posi=posi+x;
                else
                    posi=posi+" y "+x;
                

            }
            x++; // incremente en 1.
            aux = aux.sig; // por tanto el auxiliar sera igual al auxiliar siguiente.
        }
            
        return posi; // si no registro no encontrado.
    }
    public void imprimir() {
        Nodo2 reco = raiz;

        while (reco != null) {
            texto.append(reco.info + "-");
            reco = reco.sig;
        }
        texto.append("\n");
    }

    public static void main(String[] ar) {
        cola1 = new Colas();
        cola1.show();
        cola1.imprimir();

    }
}
class dibujar extends Canvas{
    Colas cola1;
    void fijar(Colas cola1){
        this.cola1=cola1;
        repaint();
    }
    public void paint(Graphics g){
        try{
    Nodo2 reco = cola1.raiz;
int posx=10;
        while (reco != null) {
            g.drawOval(posx,80,30,30);
            g.drawString(reco.info+"", posx+10, 100);
            g.drawString("->", posx+29, 100);
            posx+=40;
            reco = reco.sig;
        }
    }catch(Exception e){
        
    }
}

} 
