

DROP DATABASE IF EXISTS tienda_virtual_cl3_lp1_jchiclla;

CREATE DATABASE tienda_virtual_cl3_lp1_jchiclla;

USE tienda_virtual_cl3_lp1_jchiclla;

CREATE TABLE Productos (
	producto_id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(255),
	descripcion TEXT,
	precio DECIMAL(10,2),
	categoria VARCHAR(100),
	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Inventario (
	inventario_id INT PRIMARY KEY AUTO_INCREMENT,
	producto_id INT,
	fecha_ingreso DATE,
	cantidad INT,
	proveedor VARCHAR(255),
	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (producto_id) REFERENCES Productos(producto_id)
);
	
-- Inserción Datos
INSERT INTO Productos (nombre, descripcion, precio, categoria)
VALUES
    ('Laptop HP', 'Potente laptop para trabajo y entretenimiento.', 999.99, 'Electrónica'),
    ('Camiseta de algodón', 'Camiseta cómoda y ligera en varios colores.', 15.99, 'Ropa'),
    ('Teléfono inteligente XYZ', 'Último modelo de teléfono con cámara de alta resolución.', 799.00, 'Electrónica'),
    ('Pantalón vaquero', 'Pantalones de mezclilla de estilo clásico.', 39.99, 'Ropa'),
    ('TV LED 55 pulgadas', 'Televisor con calidad de imagen nítida y colores vivos.', 599.00, 'Electrónica'),
    ('Zapatillas deportivas', 'Zapatillas cómodas ideales para correr.', 69.99, 'Calzado'),
    ('Tablet Android', 'Tablet con pantalla táctil de alta resolución.', 199.00, 'Electrónica'),
    ('Vestido de noche', 'Elegante vestido para ocasiones especiales.', 129.99, 'Ropa'),
    ('Auriculares Bluetooth', 'Auriculares inalámbricos con cancelación de ruido.', 89.99, 'Electrónica'),
    ('Mochila resistente', 'Mochila espaciosa para llevar tus pertenencias.', 49.99, 'Accesorios');

INSERT INTO Inventario (producto_id, fecha_ingreso, cantidad, proveedor)
VALUES
	(1, '2023-08-10', 25, 'Proveedor A'),
   (1, '2023-08-11', 15, 'Proveedor B'),
   (2, '2023-08-09', 50, 'Proveedor C'),
   (2, '2023-08-10', 30, 'Proveedor D'),
   (3, '2023-08-12', 10, 'Proveedor E'),
   (3, '2023-08-12', 10, 'Proveedor E'),
   (4, '2023-08-08', 20, 'Proveedor F'),
   (5, '2023-08-10', 5, 'Proveedor G'),
   (6, '2023-08-11', 40, 'Proveedor H'),
   (7, '2023-08-09', 12, 'Proveedor I');
   
-- Consulta para actualizar producto
UPDATE Productos
SET descripcion = 'Camiseta de algodón pima en varios colores y tallas', precio = 19.9
WHERE producto_id = 2;

-- Consulta de Producto

SELECT * FROM productos;

-- Consulta de Inventario
SELECT P.producto_id, P.nombre, P.descripcion, P.precio, P.categoria, SUM(I.cantidad) AS stock
FROM Productos P
JOIN Inventario I ON P.producto_id = I.producto_id
GROUP BY P.producto_id, P.nombre, P.precio;



