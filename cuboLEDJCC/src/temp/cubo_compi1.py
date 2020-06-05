

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

x=1
y=0
a=x+y*10/4
b=x+((y*10)/4)
c=((x+y)*10)/4
d,da=20,15
e=True
f=[]
crear_lista(f,5,False)
g=f[0]
h=f[4]
i=[]
crear_lista(i,5,True)
p=i[1:4]
ah=[]
insertMatriz(ah,[[True],[True],[True]],1,-1)
al=[]
n=[]
o,p=i.shapeF,[]
q=[True,False]
q[1]=neg_func(q[1])
r=[True,False]
r[1]=hacer_true(r[1])
r[0]=hacer_false(r[0])
t=[True,False,True]
a=1
var1=2
t[var1]=neg_func(t[var1])
u=[[[True,False],[True,False]],[[True,False],[True,False]],[[True,False],[True,False]]]
le=[[[True,False],[True,False]],[[True,False],[True,False]],[[True,False],[True,False]]]
u[1][1][1]=le[1][1][1]
y=1
ac=23
def p1(a,b,c):
	for x in range(0,len(u),1):
		a=1
		for b in range(0,len(u),2):
			delete(u,1,0)


	insertMatriz(u,[[True,False,True]],0,-1)
	insertMatriz(u,[[True],[True],[True]],1,-1)
	insertMatriz(u,[[False,False,False,False]],0,0)

def miproc(z,x,y,g,h):
	m=[True,False]
	if u[:,1]==True:
		ad=m[1]
		blinku[0]True


def Main():
	if var==2:
		miproc(1,a,m[:,1],[[True],[True],[True],[True]],True)

	for xy in range(0,len(a),1):
		for bq in range(0,len(u),2):
			if a!=1:
				delete(u,0,0)
				delete(u,1,0)
				blinka[1]True
				blinka[1]False



	blinka[1][a]5SegTrue


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



