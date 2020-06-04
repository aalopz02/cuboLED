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

x=1 
a=x*10/4 
d,da=20,15 
e=true 
f=createList5false 
g=ff[0] 
p=ff[1:4] 
f.insert[[true],[true],[true]]1 
f.insert2false 
m=[] 
m.insertlena[1][t]false 
al=[] 
al.delete00 
n=[] 
n.delete2 
o,p=f.shapeF,[] 
t=[true,false,true] 
t[x].Neg 
le=[[[true,false],[true,false]],[[true,false],[true,false]],[[true,false],[true,false]]] 
le[1][1][1]=lele[1][1][1] 
le[1].T 
le[0].F 
def p1 ( a , b , c ) :
le.insert[[true,false,true]]0 
le.insert[[true],[true],[true]]1 
le.insert[[false,false,false,false]]00 
le.insert[]10 
}def miproc ( ) :
}def Main () :
}