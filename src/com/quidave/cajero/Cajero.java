/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quidave.cajero;

import com.quique.cajero.Clientes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author quique
 */
public class Cajero {

    File fich;
    private PrintWriter escribir;
    private Clientes cliente;
    private String line;

    public void registrarUsuario() {
        /**
         * Estos JOptionPane son para darle valores al constructor de Libro
         *
         * @param usu Usuario del cliente
         * @param ctra Contraseña de su cuenta
         * @param nom Nombre del cliente
         * @param saldo Saldo de su cuenta
         */
        String usu = JOptionPane.showInputDialog("Usuario");

        //Mientras que el usuario sea un nulo o un valor en blanco que no te deje meterlo.
        while (usu.equals("") || usu == null) {
            usu = JOptionPane.showInputDialog("Usuario inválido.");
        }

        try {

            fich = new File("cajero.txt");
            BufferedReader leer = new BufferedReader(new FileReader("cajero.txt"));
            String line = "";

            while ((line = leer.readLine()) != null) {

                //El indexOf si no encuentra el valor que le metes devuelve un -1, por eso la comparacion
                //Si la busqueda es distinto de -1
                if (line.indexOf(usu) != -1) {
                    usu = JOptionPane.showInputDialog("Usuario existente.");
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error. No se ha encontrado. " + ex);
        } catch (IOException ex) {
            System.out.println("Error. " + ex);
        }

        String ctra = JOptionPane.showInputDialog("Contraseña");

        while (ctra.equals("") || ctra == null) {
            ctra = JOptionPane.showInputDialog("Contraseña inválida.");

        }

        String nom = JOptionPane.showInputDialog("Nombre");
        float saldo = Float.parseFloat(JOptionPane.showInputDialog("Saldo de la cuenta"));

        try {
            /**
             * Fich pasa a ser un nuevo archivo file
             *
             * @param escribir pasa a ser un nuevo PrintWriter que tiene dentro
             * un FileWriter con el fichero.
             *
             * Introducimos el objeto de tipo libro en el fichero.
             */
            fich = new File("cajero.txt");
            escribir = new PrintWriter(new FileWriter(fich, true));
            escribir.println(cliente = new Clientes(usu, ctra, nom, saldo));

        } catch (FileNotFoundException ex) {
            /**
             * Excepcion mas estricta Excepcion general
             *
             * @param ex Excepcion de tipo FileNotFoundException
             * @param ex (Segundo ex) Excepcion de tipo IOException
             */
            System.out.println("Error de escritura" + ex);

        } catch (IOException ex) {
            System.out.println("Error de escritura" + ex);
        } finally {
            escribir.close();
        }

    }
}
