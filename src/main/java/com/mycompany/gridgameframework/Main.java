/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

/**
 *
 * @author Pete
 */
public class Main {
    
    public static Set loadClasses(){
                List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader()); 
        classLoadersList.add(ClasspathHelper.staticClassLoader());

Reflections reflections = new Reflections(new ConfigurationBuilder()
    .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.mycompany.gridgameframework"))));
        
        Set<Class<? extends Object>> subTypes = reflections.getSubTypesOf(Object.class);
        
        return subTypes;
    }
    
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Set<Class<? extends Object>> subTypes = loadClasses();
        for (Class obj : subTypes){
            //obj.class.getName();
            
            System.out.println(obj);
            if(obj.getSimpleName().equals("SingleGame")){
                test(obj);
            }
        }
    }
    
    public static void test(Class<?> clazz) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
       Constructor<?> ctor = clazz.getConstructor(int.class);
        SingleGame game = (SingleGame) ctor.newInstance( 3 );
        game.hello();
    }
}
