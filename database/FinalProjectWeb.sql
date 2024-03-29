USE [WebBanHoaQua]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 4/18/2020 12:44:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[userName] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NULL,
	[type] [nvarchar](50) NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 4/18/2020 12:44:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[CustomerID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerName] [nvarchar](50) NULL,
	[Phone] [varchar](50) NULL,
	[Address] [nvarchar](50) NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employess]    Script Date: 4/18/2020 12:44:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employess](
	[id] [nvarchar](50) NOT NULL,
	[name] [nvarchar](50) NULL,
	[dob] [date] NULL,
	[gender] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
 CONSTRAINT [PK_Employess] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderIn]    Script Date: 4/18/2020 12:44:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderIn](
	[OrderInID] [nvarchar](50) NOT NULL,
	[DateAdd] [date] NULL,
	[EmployeeID] [nvarchar](50) NULL,
	[SuplierID] [nvarchar](50) NULL,
	[Total] [float] NULL,
 CONSTRAINT [PK_OrderIn] PRIMARY KEY CLUSTERED 
(
	[OrderInID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderInDetail]    Script Date: 4/18/2020 12:44:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderInDetail](
	[OrderInID] [nvarchar](50) NOT NULL,
	[ProductID] [nvarchar](50) NULL,
	[Quantity] [float] NULL,
	[Price] [float] NULL,
	[Total] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderOut]    Script Date: 4/18/2020 12:44:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderOut](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [int] NOT NULL,
	[Total] [float] NULL,
	[OrderDate] [date] NULL,
	[Type] [bit] NULL,
	[Note] [nvarchar](250) NULL,
	[EmployeeID] [nvarchar](50) NULL,
 CONSTRAINT [PK_OrderOut] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderOutDetail]    Script Date: 4/18/2020 12:44:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderOutDetail](
	[OderID] [int] NOT NULL,
	[ProductID] [nvarchar](50) NOT NULL,
	[Quantity] [float] NULL,
	[Total] [float] NULL,
	[Sale] [float] NULL,
	[ProductPrice] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 4/18/2020 12:44:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProductID] [nvarchar](50) NOT NULL,
	[ProductName] [nvarchar](50) NULL,
	[CostPrice] [float] NULL,
	[SellingPrice] [float] NULL,
	[Quantity] [float] NULL,
	[Note] [nvarchar](50) NULL,
 CONSTRAINT [PK_Product_1] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Supplier]    Script Date: 4/18/2020 12:44:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Supplier](
	[SupplierID] [nvarchar](50) NOT NULL,
	[SupplierName] [nchar](10) NULL,
	[Address] [nvarchar](50) NULL,
	[PhoneNumber] [int] NULL,
	[Email] [nvarchar](50) NULL,
	[Type] [nvarchar](50) NULL,
 CONSTRAINT [PK_Supplier] PRIMARY KEY CLUSTERED 
(
	[SupplierID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([userName], [Password], [type]) VALUES (N'tuan', N'123', NULL)
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [Phone], [Address]) VALUES (1, N'Khách vãn lai', N'NULL', N'NULL')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [Phone], [Address]) VALUES (2, N'Đỗ Trọng Anh Tuấn', N'01238689529', N'Số 4 ngách 3/15 Vân Hồ 3')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [Phone], [Address]) VALUES (3, N'Võ Khắc Việt', N'0123456789', N'Cà Mau')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [Phone], [Address]) VALUES (4, N'Nguyễn Mạnh Kiên', N'01238689529', N'Vân Hồ 3')
SET IDENTITY_INSERT [dbo].[Customer] OFF
INSERT [dbo].[Employess] ([id], [name], [dob], [gender], [address]) VALUES (N'tuan', N'Đỗ Trọng Anh Tuấn', CAST(N'1999-04-15' AS Date), N'1', N'Vân Hồ 3')
SET IDENTITY_INSERT [dbo].[OrderOut] ON 

INSERT [dbo].[OrderOut] ([OrderID], [CustomerID], [Total], [OrderDate], [Type], [Note], [EmployeeID]) VALUES (1, 2, 1822, CAST(N'2019-11-11' AS Date), 1, N'Giao Hàng Lúc 9h sáng', N'tuan')
INSERT [dbo].[OrderOut] ([OrderID], [CustomerID], [Total], [OrderDate], [Type], [Note], [EmployeeID]) VALUES (2, 1, 375, CAST(N'2019-11-10' AS Date), 1, NULL, N'tuan')
INSERT [dbo].[OrderOut] ([OrderID], [CustomerID], [Total], [OrderDate], [Type], [Note], [EmployeeID]) VALUES (3, 3, 290, CAST(N'2019-11-12' AS Date), 0, N'Giao lúc 12h', N'tuan')
INSERT [dbo].[OrderOut] ([OrderID], [CustomerID], [Total], [OrderDate], [Type], [Note], [EmployeeID]) VALUES (4, 2, 844, CAST(N'2019-11-12' AS Date), 1, N'Ghi chú', N'tuan')
INSERT [dbo].[OrderOut] ([OrderID], [CustomerID], [Total], [OrderDate], [Type], [Note], [EmployeeID]) VALUES (5, 2, 60, CAST(N'2019-11-08' AS Date), 1, N'Giao vao luc 9h', N'tuan')
INSERT [dbo].[OrderOut] ([OrderID], [CustomerID], [Total], [OrderDate], [Type], [Note], [EmployeeID]) VALUES (6, 2, 240, CAST(N'2020-01-03' AS Date), 0, N'abcxyz', N'tuan')
INSERT [dbo].[OrderOut] ([OrderID], [CustomerID], [Total], [OrderDate], [Type], [Note], [EmployeeID]) VALUES (7, 2, 540, CAST(N'2020-01-09' AS Date), 0, N'1242', N'tuan')
INSERT [dbo].[OrderOut] ([OrderID], [CustomerID], [Total], [OrderDate], [Type], [Note], [EmployeeID]) VALUES (8, 4, 3000, CAST(N'2020-01-06' AS Date), 1, NULL, N'tuan')
INSERT [dbo].[OrderOut] ([OrderID], [CustomerID], [Total], [OrderDate], [Type], [Note], [EmployeeID]) VALUES (9, 4, 129960, CAST(N'2020-01-04' AS Date), 0, N'', N'tuan')
SET IDENTITY_INSERT [dbo].[OrderOut] OFF
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (1, N'Xoài101', 3.7000000476837158, 222, NULL, 60)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (2, N'Chôm101', 2.5, 150, NULL, 60)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (2, N'Cam101', 5, 225, NULL, 45)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (3, N'Cam102', 1, 20, NULL, 20)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (3, N'Cam101', 2, 90, NULL, 45)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (3, N'Chôm101', 3, 180, NULL, 60)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (5, N'Chôm101', 1, 60, NULL, 60)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (6, N'Cam102', 12, 240, NULL, 20)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (7, N'Cam101', 12, 540, NULL, 45)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (9, N'Cam101', 456, 20520, NULL, 45)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (9, N'Nho101', 456, 91200, NULL, 200)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (9, N'Vải111', 456, 18240, NULL, 40)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (1, N'TáoEV101', 10, 1600, NULL, 160)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (4, N'Dưa101', 2, 44, NULL, 22)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (4, N'Nho101', 4, 800, NULL, 200)
INSERT [dbo].[OrderOutDetail] ([OderID], [ProductID], [Quantity], [Total], [Sale], [ProductPrice]) VALUES (8, N'Nhãn101', 50, 3000, NULL, 60)
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'Cam101', N'Cam Xanh', 35, 45, 25, NULL)
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'Cam102', N'Cam Hưng Yên', 16, 20, 30, N'null')
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'Cam113', N'Cam Hưng Yên', 16, 30, 101, N'')
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'Chôm101', N'Chôm Nhãn', 50, 60, 26.5, N'null')
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'Dưa101', N'Dưa Hấu', 18, 22, 38, NULL)
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'Nhãn101', N'Nhãn Thái', 50, 60, -20, NULL)
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'Nho101', N'Nho Mỹ', 180, 200, 26, NULL)
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'QuytSG', N'Quýt Sài Gòn', 45, 55, 30, NULL)
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'TáoEV101', N'Táo Envy', 145, 160, 20, NULL)
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'Vải111', N'Vài thiều', 30, 40, 100, N'Nóng')
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'Vải112', N'Vài thiều Hưng Yên', 40, 60, 100, N'Null')
INSERT [dbo].[Product] ([ProductID], [ProductName], [CostPrice], [SellingPrice], [Quantity], [Note]) VALUES (N'Xoài101', N'Xoài Cát', 50, 60, 26.299999237060547, NULL)
INSERT [dbo].[Supplier] ([SupplierID], [SupplierName], [Address], [PhoneNumber], [Email], [Type]) VALUES (N'1', N'Cô Lang   ', N'Thái Phiên', 98754456, NULL, N'Chủ Bán Quýt')
INSERT [dbo].[Supplier] ([SupplierID], [SupplierName], [Address], [PhoneNumber], [Email], [Type]) VALUES (N'2', N'Bác Xuyên ', N'Trương Định', 32165487, NULL, N'Chủ Bán Xoài')
INSERT [dbo].[Supplier] ([SupplierID], [SupplierName], [Address], [PhoneNumber], [Email], [Type]) VALUES (N'3', N'Bác Hà    ', N'Vân Hồ', 1321323, NULL, N'Chủ Cam Xanh')
ALTER TABLE [dbo].[Employess]  WITH CHECK ADD  CONSTRAINT [FK_Employess_Account] FOREIGN KEY([id])
REFERENCES [dbo].[Account] ([userName])
GO
ALTER TABLE [dbo].[Employess] CHECK CONSTRAINT [FK_Employess_Account]
GO
ALTER TABLE [dbo].[OrderIn]  WITH CHECK ADD  CONSTRAINT [FK_OrderIn_Employess] FOREIGN KEY([EmployeeID])
REFERENCES [dbo].[Employess] ([id])
GO
ALTER TABLE [dbo].[OrderIn] CHECK CONSTRAINT [FK_OrderIn_Employess]
GO
ALTER TABLE [dbo].[OrderIn]  WITH CHECK ADD  CONSTRAINT [FK_OrderIn_Supplier] FOREIGN KEY([SuplierID])
REFERENCES [dbo].[Supplier] ([SupplierID])
GO
ALTER TABLE [dbo].[OrderIn] CHECK CONSTRAINT [FK_OrderIn_Supplier]
GO
ALTER TABLE [dbo].[OrderInDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderInDetail_OrderIn] FOREIGN KEY([OrderInID])
REFERENCES [dbo].[OrderIn] ([OrderInID])
GO
ALTER TABLE [dbo].[OrderInDetail] CHECK CONSTRAINT [FK_OrderInDetail_OrderIn]
GO
ALTER TABLE [dbo].[OrderInDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderInDetail_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[OrderInDetail] CHECK CONSTRAINT [FK_OrderInDetail_Product]
GO
ALTER TABLE [dbo].[OrderOut]  WITH CHECK ADD  CONSTRAINT [FK_OrderOut_Customer] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer] ([CustomerID])
GO
ALTER TABLE [dbo].[OrderOut] CHECK CONSTRAINT [FK_OrderOut_Customer]
GO
ALTER TABLE [dbo].[OrderOut]  WITH CHECK ADD  CONSTRAINT [FK_OrderOut_Employess] FOREIGN KEY([EmployeeID])
REFERENCES [dbo].[Employess] ([id])
GO
ALTER TABLE [dbo].[OrderOut] CHECK CONSTRAINT [FK_OrderOut_Employess]
GO
ALTER TABLE [dbo].[OrderOutDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderOutDetail_OrderOut] FOREIGN KEY([OderID])
REFERENCES [dbo].[OrderOut] ([OrderID])
GO
ALTER TABLE [dbo].[OrderOutDetail] CHECK CONSTRAINT [FK_OrderOutDetail_OrderOut]
GO
ALTER TABLE [dbo].[OrderOutDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderOutDetail_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[OrderOutDetail] CHECK CONSTRAINT [FK_OrderOutDetail_Product]
GO
