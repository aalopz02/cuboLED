while True:
    for i in range(0,len(lista_valores)):
        if(lista_valores[i]==False):
            ser.write(b'0')

        if(lista_valores[i]==True):
            ser.write(b'1')
    for x in range(0,len(lista_valores)):
        lista_tiempos_aux[x]=lista_tiempos_aux[x]-1
        if(lista_tiempos_aux[x]==0):
            if(lista_valores[x]==False):
                lista_valores[x]=True
            else:
                lista_valores[x]=False
            lista_tiempos_aux[x]=lista_tiempos[x];
    time.sleep(0.02)
ser.close





import serial
import time

connected= False

ser=serial.Serial("COM3",9600)
lista_valores=[True,True,True,True,True,True,True,True,   False,False,False,False,False,False,False,False,  False,True,False,True,False,True]
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

def neg_func(lista_a_negar):
    if (type(lista_a_negar) == list):
        for i in range(0,len(lista_a_negar)):
            if lista_a_negar[i]==True:
                lista_a_negar[i]=False
            else:
                lista_a_negar[i]=True
        return lista_a_negar
    else:
        if lista_a_negar==True:
            return False
        else:
            return True

def hacer_true(lista):
    if (type(lista) == list):
        for i in range(0,len(lista)):
            lista[i] = True
        return lista
    else:
        return True

def hacer_false(lista):
    if (type(lista) == list):
        for i in range(0,len(lista)):
            lista[i] = False
        return lista
    else:
        return False

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

def delete(lista,index,tipo):
    if (tipo == 0):
        lista.pop(index)
    else:
        for i in range(0,len(lista)):
            if (type(lista[i]) == list):
                lista[i].pop(index)
            else:
                lista.pop(i)


def shapeF(lista):
    return len(lista)

def shapeC(lista):
    if (type(lista[0]) == list):
        print(type(lista[0]))
        return len(lista[0])
    else:
        for i in range(0,len(lista)):
            if (type(lista[i]) == list):
                return len(lista[i])
        return 0

def insertMatriz(lista,valor,tipo,indice):
    if (tipo==0 and indice == -1):
        lista += [valor]
    elif (tipo == 0 and indice != -1):
        lista.insert(indice,valor)
    elif (tipo == 1 and indice == -1):
        for i in range(0,len(lista)):
            if (type(lista[i]) == list):
                lista[i] += valor[i]
            else:
                lista[i] = [valor[i]]
    else:
        for i in range(0,len(lista)):
            if (type(lista[i]) == list):
                lista[i].insert(indice,valor[i][0])
            else:
                lista[i] = valor[i]

def insertLista(lista,valor,indice):
    lista.insert(indice,valor)

import time

def delay(period,unit):
    if (unit == 1):
        time.sleep(period)
    elif (unit == 2):
        time.sleep(period/1000)
    else:
        time.sleep(period*60)