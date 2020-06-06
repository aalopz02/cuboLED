import serial
import time
import numpy as np
import math

from itertools import product
from threading import Thread

timer=1000;
rango_timer=1


matriz_cubo=[[[False,False,False,False,False,False,False,True],
            [False,False,False,False,False,False,False,True],
            [False,False,False,False,False,False,False,True],
            [False,False,False,False,False,False,False,True],
            [False,False,False,False,False,False,False,True],
            [False,False,False,False,False,False,False,True],
            [False,False,False,False,False,False,False,True],
            [False,False,False,False,False,False,False,True]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]]]

matriz_tiempos=[[[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]]]
matriz_tiempos_aux=[[[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]],

            [[False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False],
            [False,False,False,False,False,False,False,False]]]

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

def delay(period,unit):
    if (unit == 1):
        time.sleep(period)
    elif (unit == 2):
        time.sleep(period/1000)
    else:
        time.sleep(period*60)

def delayDefault():
    if (rango_timer == 1):
        time.sleep(timer)
    elif (rango_timer == 2):
        time.sleep(timer/1000)
    else:
        time.sleep(timer*60)

def getLen(var):
    if (type(var)==list):
        return len(var)
    else:
        return var

def blink_fun(i,j,k,blink_value,time_unit,bool_value):
    a=i
    b=j
    c=k
    if type(i)==bool:
        a=0
        b=0
        c=0
        print("Entre")
        i=6
        j=8
        k=8
    else:
        if type(i)==int:
            i=i+1

        if type(j)==int:
            j=j+1
        if type(j)==bool:
            b=0
            j=8

        if type(k)==int:
            k=k+1
        if type(k)==bool:
            c=0
            k=8
    print(a,b,c,i,j,k)
    for z1 in range(a,i):
        for z2 in range(b,j):
            for z3 in range(c,k):
                if bool_value==True:
                    matriz_cubo[z1][z2][z3]=True

                    if time_unit==-1:
                        time_unit=rango_timer
                        blink_value=timer

                    if time_unit==1:
                        matriz_tiempos[z1][z2][z3]=int(math.ceil(blink_value/400))
                        matriz_tiempos_aux[z1][z2][z3]=int(math.ceil(blink_value/400))

                    if time_unit==2:
                        matriz_tiempos[z1][z2][z3]=int(math.ceil((blink_value*1000)/400))
                        matriz_tiempos_aux[z1][z2][z3]=int(math.ceil((blink_value*1000)/400))
                    if time_unit==3:
                        matriz_tiempos[z1][z2][z3]=int(math.ceil((blink_value*1000*60)/400))
                        matriz_tiempos_aux[z1][z2][z3]=int(math.ceil((blink_value*1000*60)/400))


                elif bool_value==False:
                    matriz_tiempos[z1][z2][z3]=False
                    matriz_tiempos_aux[z1][z2][z3]=False



def while_cycle():
    connected= False
    ser=serial.Serial("COM3",9600)
    time.sleep(2)

    while True:
        for i in range(6):
            if i%2==0:
                for j in range(8):
                    if j%2==0:
                        for k in range(8):
                            if(matriz_cubo[i][j][k]==False):
                                ser.write(b'0')
                            if(matriz_cubo[i][j][k]==True):
                                ser.write(b'1')
                            matriz_tiempos_aux[i][j][k]=matriz_tiempos_aux[i][j][k]-1
                            if(matriz_tiempos_aux[i][j][k]==0):
                                if(matriz_cubo[i][j][k]==False):
                                    matriz_cubo[i][j][k]=True
                                else:
                                    matriz_cubo[i][j][k]=False

                                matriz_tiempos_aux[i][j][k]=matriz_tiempos[i][j][k];
                    elif j%2!=0:
                        for k in reversed(range(8)):
                            if(matriz_cubo[i][j][k]==False):
                                ser.write(b'0')
                            if(matriz_cubo[i][j][k]==True):
                                ser.write(b'1')
                            matriz_tiempos_aux[i][j][k]=matriz_tiempos_aux[i][j][k]-1
                            if(matriz_tiempos_aux[i][j][k]==0):
                                if(matriz_cubo[i][j][k]==False):
                                    matriz_cubo[i][j][k]=True
                                else:
                                    matriz_cubo[i][j][k]=False
                                matriz_tiempos_aux[i][j][k]=matriz_tiempos[i][j][k];

            elif i%2!=0:
                for j in reversed(range(8)):
                    if j%2==0:
                        for k in range(8):
                            if(matriz_cubo[i][j][k]==False):
                                ser.write(b'0')
                            if(matriz_cubo[i][j][k]==True):
                                ser.write(b'1')
                            matriz_tiempos_aux[i][j][k]=matriz_tiempos_aux[i][j][k]-1
                            if(matriz_tiempos_aux[i][j][k]==0):
                                if(matriz_cubo[i][j][k]==False):
                                    matriz_cubo[i][j][k]=True
                                else:
                                    matriz_cubo[i][j][k]=False
                                matriz_tiempos_aux[i][j][k]=matriz_tiempos[i][j][k];
                    elif j%2!=0:
                        for k in reversed(range(8)):
                            if(matriz_cubo[i][j][k]==False):
                                ser.write(b'0')
                            if(matriz_cubo[i][j][k]==True):
                                ser.write(b'1')
                            matriz_tiempos_aux[i][j][k]=matriz_tiempos_aux[i][j][k]-1
                            if(matriz_tiempos_aux[i][j][k]==0):
                                if(matriz_cubo[i][j][k]==False):
                                    matriz_cubo[i][j][k]=True
                                else:
                                    matriz_cubo[i][j][k]=False
                                matriz_tiempos_aux[i][j][k]=matriz_tiempos[i][j][k];




        time.sleep(0.015)


    ser.close


timer = 1
rango_timer = 1
cubo=matriz_cubo

x=1
y=0
a=x+y*10/4
b=x+((y*10)/4)
c=((x+y)*10)/4
d,da=20,15
e=True
f=[]
crear_lista(f,5,False)
h=f[4]
i=[]
crear_lista(i,5,True)
ah=[]
insertMatriz(ah,[[True],[True],[True]],1,-1)
al=[]
n=[]
o,p=shapeF(i),2
q=[True,False]
q[1]=neg_func(q[1])
r=[True,False]
r[1]=hacer_true(r[1])
r[0]=hacer_false(r[0])
t=[True,False,True]
a=shapeC(q)
var1=len(r)
t[var1]=neg_func(t[var1])
u=[[[True,False],[True,False]],[[True,False],[True,False]],[[True,False],[True,False]]]
le=[[[True,False],[True,False]],[[True,False],[True,False]],[[True,False],[True,False]]]
u[1][1][1]=le[1][1][1]
y=1
ac=23
pt=[[True,True,True],[False,False,False]]
def p1(p,lk,pu):
	for x in range(0,getLen(p),1):
		a=lk
		for b in range(0,getLen(pu),2):
			y=b


	blink_fun(1,0,3,5,1,True)
	insertMatriz(pt,[[True,False,True]],0,-1)
	insertMatriz(pt,[[True],[True],[True]],1,-1)

def miproc(z,x,y,g,h):
	blink_fun(True,True,True,-1,-1,False)
	m=[True,False]
	if u==True:
		ad=m[z]
		delayDefault()

	p1(0,0,1)

def MainProc():
	if var1==2:
		miproc(1,2,4,[[True],[True],[True],[True]],True)

	for xy in range(0,getLen(a),1):
		for bq in range(0,getLen(u),2):
			if a!=1:
				delete(u,0,0)
				delete(u,1,0)
				delay(6,1)



	delay(5,2)



def main():
    time.sleep(2)
    t = Thread(target = while_cycle, args =())
    t.start()
    MainProc()
    hola=[]
    a=0.032;
    print(int(a))
    print(int(math.ceil(a)))
    #matriz_cubo[5][5][5]=True
    time.sleep(1);
    blink_fun(True,True,True,400,1,True)


    p=[0,1,2,3,4]




#delete l.pop(#)

if __name__ == "__main__":
    main()
