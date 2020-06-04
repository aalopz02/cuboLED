while True:
    for i in range(0,len(lista_valores)):
        if(lista_valores[i]==0):
            lista_escritura[i]==0
            ser.write(b'0')

        if(lista_valores[i]==1):
            ser.write(b'1')

            lista_escritura[i]==1
    for x in range(0,len(lista_valores)):
        lista_tiempos_aux[x]=lista_tiempos_aux[x]-1
        if(lista_tiempos_aux[x]==0):
            if(lista_valores[x]==0):
                lista_valores[x]=1
            else:
                lista_valores[x]=0
            lista_tiempos_aux[x]=lista_tiempos[x];
    time.sleep(0.02)
ser.close



import serial
import time

connected= False

ser=serial.Serial("COM3",9600)
lista_valores=[1,1,1,1,1,1,1,1,   0,0,0,0,0,0,0,0,  0,1,0,1,0,1]
lista_tiempos=[10,10,10,10,10,10,10,10,   50,50,50,50,50,50,50,50,  1,1,1,1,1,1]
#lista_tiempos=[1000,1000,1000,1000,1000,1000,1000,1000,   5000,5000,5000,5000,5000,5000,5000,5000,  10000,10000,10000,10000,10000,10000]
lista_tiempos_aux=lista_tiempos[:]
lista_escritura=[1,1,1,1,1,1,1,1,   0,0,0,0,0,0,0,0,  0,1,0,1,0,1];
time.sleep(2)

def crear_lista(lista_name,range_val, bool_val):
    lista_name.clear
    for i in range(0,range_val):
        lista_name.append(bool_val)

    return lista_name

def neg_func(lista_a_negar, num):
    if lista_a_negar[num]==True:
        lista_a_negar[num]=False
    else:
        lista_a_negar[num]=True

def hacer_true(lista,ind):
    lista[ind]=True

def hacer_true_range(lista,low,high):
    for i in range(low,high):
        lista[i]=True

def hacer_false(lista,ind):
    lista[ind]=False

def hacer_false_range(lista,low,high):
    for i in range(low,high):
        lista[i]=False

def blink_fun(lista1,lista2,lista3,num,blink_value,bool_value,time_unit):
    lista1[num]=bool_value
    if time_unit=="Mil":
        lista2[num]=blink_value
        lista3[num]=blink_value
    if time_unit=="Seg":
        lista2[num]=blink_value*1000
        lista3[num]=blink_value*1000
    if time_unit=="Min":
        lista2[num]=blink_value*1000*60
        lista3[num]=blink_value*1000*60