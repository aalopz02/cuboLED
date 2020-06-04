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
asdcvascscsdNAx=1 
y=0 
a=x+y*10/4 
b=x+((y*10)/4) 
c=((x+yy)*10)/4 
d,da=20,15 
e=true 
f=createList5false 
g=ff[0] 
h=ff[4] 
i=createList5true 
p=ii[1:4] 
ah=[] 
ah.insert[[true],[true],[true]]1 
l=[] 
l.insert2false 
m=[] 
m.insertlena[1][t]false 
al=[] 
al.delete00 
n=[] 
n.delete2 
o,p=i.shapeF,[] 
q=[true,false] 
q[1].Neg 
r=[true,false] 
r[1].T 
r[0].F 
t=[true,false,true] 
a=1 
var1=2 
t[var1].Neg 
u=[[[true,false],[true,false]],[[true,false],[true,false]],[[true,false],[true,false]]] 
le=[[[true,false],[true,false]],[[true,false],[true,false]],[[true,false],[true,false]]] 
u[1][1][1]=lele[1][1][1] 
y=1 
u[:,1]=[] 
ac=23 
:
defp1(a,b,c){lista=[] 
forxinlistastep1{a=1 
forbinlistastep2{u.delete10 
}}aif=[true,false] 
ifaif[:,1]==true{ad=uu[1] 
ae=uu[1] 
af=uu[:,1] 
shapeC}ua[c.shapeC][ab].insert[[true,false,true]]0 
u.insert[[true],[true],[true]]1 
u.insert[[false,false,false,false]]00 
u.insert[]10 
}:
defmiproc(){}:
defMain(){ifvar==2{callp11,aa,mm}forxyinastep1{callmiprocforbqinlistastep2{iflista==true{u.delete00 
u.delete10 
}}}}