USE [master]
GO
/****** Object:  Database [MockApi]    Script Date: 12/29/2022 11:58:11 AM ******/
CREATE DATABASE [MockApi]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'MockApi', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.QUOCTRONG\MSSQL\DATA\MockApi.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'MockApi_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.QUOCTRONG\MSSQL\DATA\MockApi_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [MockApi] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MockApi].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MockApi] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MockApi] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MockApi] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MockApi] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MockApi] SET ARITHABORT OFF 
GO
ALTER DATABASE [MockApi] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MockApi] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MockApi] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MockApi] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MockApi] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MockApi] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MockApi] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MockApi] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MockApi] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MockApi] SET  DISABLE_BROKER 
GO
ALTER DATABASE [MockApi] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MockApi] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MockApi] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MockApi] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MockApi] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MockApi] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MockApi] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MockApi] SET RECOVERY FULL 
GO
ALTER DATABASE [MockApi] SET  MULTI_USER 
GO
ALTER DATABASE [MockApi] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MockApi] SET DB_CHAINING OFF 
GO
ALTER DATABASE [MockApi] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [MockApi] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [MockApi] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [MockApi] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'MockApi', N'ON'
GO
ALTER DATABASE [MockApi] SET QUERY_STORE = OFF
GO
USE [MockApi]
GO
/****** Object:  UserDefinedFunction [dbo].[Utf8ToUcs]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Function [dbo].[Utf8ToUcs](@src varchar(MAX)) returns nvarchar(MAX) as
begin
    declare @i int, @res nvarchar(MAX)=@src, @pi varchar(18)
    select @pi='%[à-ï][€-¿][€-¿]%',@i=patindex(@pi,@src collate Latin1_General_BIN)
    while @i>0 select @res=stuff(@res,@i,3,nchar(((ascii(substring(@src,@i,1))&31)*4096)+((ascii(substring(@src,@i+1,1))&63)*64)+(ascii(substring(@src,@i+2,1))&63))), @src=stuff(@src,@i,3,'.'), @i=patindex(@pi,@src collate Latin1_General_BIN)
    select @pi='%[Â-ß][€-¿]%',@i=patindex(@pi,@src collate Latin1_General_BIN)
    while @i>0 select @res=stuff(@res,@i,2,nchar(((ascii(substring(@src,@i,1))&31)*64)+(ascii(substring(@src,@i+1,1))&63))), @src=stuff(@src,@i,2,'.'),@i=patindex(@pi,@src collate Latin1_General_BIN)
    return @res
end
GO
/****** Object:  Table [dbo].[category]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[create_at] [datetime2](7) NOT NULL,
	[name] [varchar](255) NULL,
	[update_at] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[counpon]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[counpon](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[des_cription] [varchar](255) NULL,
	[total] [int] NULL,
	[use_value] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[create_at] [datetime2](7) NOT NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[update_at] [datetime2](7) NULL,
	[order_id] [int] NULL,
	[product_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[create_at] [datetime2](7) NOT NULL,
	[order_status] [int] NULL,
	[update_at] [datetime2](7) NULL,
	[coupon_id] [int] NOT NULL,
	[shipping_id] [int] NOT NULL,
	[user_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[content] [varchar](255) NULL,
	[create_at] [datetime2](7) NOT NULL,
	[image_path] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[price] [int] NULL,
	[update_at] [datetime2](7) NULL,
	[category_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_image]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_image](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
	[path] [varchar](255) NULL,
	[product_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[revenues]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[revenues](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_date] [varchar](255) NULL,
	[profit] [int] NULL,
	[sales] [int] NULL,
	[total_order] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[shipping]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shipping](
	[shipping_id] [int] IDENTITY(1,1) NOT NULL,
	[shipping_address] [varchar](255) NULL,
	[shipping_name] [varchar](255) NULL,
	[shipping_note] [varchar](255) NULL,
	[shipping_phone] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[shipping_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_roles]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_roles](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_users]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[email] [varchar](255) NULL,
	[passwork] [varchar](255) NULL,
	[username] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_role]    Script Date: 12/29/2022 11:58:11 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_role](
	[user_id] [bigint] NOT NULL,
	[role_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([id], [create_at], [name], [update_at]) VALUES (6, CAST(N'2022-07-25T15:26:20.4150000' AS DateTime2), N'Road Bikes', CAST(N'2022-07-25T15:26:20.4150000' AS DateTime2))
INSERT [dbo].[category] ([id], [create_at], [name], [update_at]) VALUES (7, CAST(N'2022-07-25T15:29:54.2190000' AS DateTime2), N'Mountain Bikes', CAST(N'2022-07-25T15:29:54.2190000' AS DateTime2))
INSERT [dbo].[category] ([id], [create_at], [name], [update_at]) VALUES (8, CAST(N'2022-07-25T15:30:10.8390000' AS DateTime2), N'Kid''s Bikes', CAST(N'2022-07-25T15:30:10.8390000' AS DateTime2))
INSERT [dbo].[category] ([id], [create_at], [name], [update_at]) VALUES (15, CAST(N'2022-08-16T13:51:47.1810000' AS DateTime2), N'Fines', CAST(N'2022-08-17T13:30:16.1160000' AS DateTime2))
INSERT [dbo].[category] ([id], [create_at], [name], [update_at]) VALUES (19, CAST(N'2022-08-17T15:18:24.5340000' AS DateTime2), N'Kona', CAST(N'2022-08-17T15:18:24.5340000' AS DateTime2))
SET IDENTITY_INSERT [dbo].[category] OFF
GO
SET IDENTITY_INSERT [dbo].[counpon] ON 

INSERT [dbo].[counpon] ([id], [des_cription], [total], [use_value]) VALUES (7, N'Applicable for orders over ', 200, 10)
INSERT [dbo].[counpon] ([id], [des_cription], [total], [use_value]) VALUES (9, N'Applicable for orders over ', 700, 15)
INSERT [dbo].[counpon] ([id], [des_cription], [total], [use_value]) VALUES (10, N'Applicable for orders over ', 900, 20)
INSERT [dbo].[counpon] ([id], [des_cription], [total], [use_value]) VALUES (13, N'Applicable for orders over 100', 100, 5)
INSERT [dbo].[counpon] ([id], [des_cription], [total], [use_value]) VALUES (14, N'default', 0, 0)
SET IDENTITY_INSERT [dbo].[counpon] OFF
GO
SET IDENTITY_INSERT [dbo].[order_detail] ON 

INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (1, CAST(N'2022-08-02T15:03:25.6600000' AS DateTime2), 2000, 1, CAST(N'2022-08-02T15:03:25.6600000' AS DateTime2), 1, 19)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (2, CAST(N'2022-08-02T15:03:25.6690000' AS DateTime2), 701, 1, CAST(N'2022-08-02T15:03:25.6690000' AS DateTime2), 1, 16)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (3, CAST(N'2022-08-03T10:22:28.7630000' AS DateTime2), 2000, 2, CAST(N'2022-08-03T10:22:28.7630000' AS DateTime2), 2, 19)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (4, CAST(N'2022-08-03T10:22:28.7930000' AS DateTime2), 701, 1, CAST(N'2022-08-03T10:22:28.7930000' AS DateTime2), 2, 16)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (5, CAST(N'2022-08-03T17:06:23.8690000' AS DateTime2), 2000, 1, CAST(N'2022-08-03T17:06:23.8690000' AS DateTime2), 3, 19)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (6, CAST(N'2022-08-03T17:06:23.8750000' AS DateTime2), 701, 1, CAST(N'2022-08-03T17:06:23.8750000' AS DateTime2), 3, 16)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (7, CAST(N'2022-08-03T17:06:23.8790000' AS DateTime2), 700, 1, CAST(N'2022-08-03T17:06:23.8790000' AS DateTime2), 3, 17)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (8, CAST(N'2022-08-03T17:07:55.2310000' AS DateTime2), 500, 1, CAST(N'2022-08-03T17:07:55.2310000' AS DateTime2), 4, 15)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (9, CAST(N'2022-08-03T17:08:39.0440000' AS DateTime2), 701, 1, CAST(N'2022-08-03T17:08:39.0440000' AS DateTime2), 5, 16)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (10, CAST(N'2022-08-03T17:33:41.3890000' AS DateTime2), 2000, 1, CAST(N'2022-08-03T17:33:41.3890000' AS DateTime2), 6, 19)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (11, CAST(N'2022-08-03T17:33:41.3930000' AS DateTime2), 701, 1, CAST(N'2022-08-03T17:33:41.3930000' AS DateTime2), 6, 16)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (12, CAST(N'2022-08-03T17:33:41.3980000' AS DateTime2), 700, 1, CAST(N'2022-08-03T17:33:41.3980000' AS DateTime2), 6, 17)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (13, CAST(N'2022-08-03T17:34:20.6200000' AS DateTime2), 500, 1, CAST(N'2022-08-03T17:34:20.6200000' AS DateTime2), 7, 15)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (14, CAST(N'2022-08-04T00:41:20.1770000' AS DateTime2), 100, 1, CAST(N'2022-08-04T00:41:20.1770000' AS DateTime2), 8, 13)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (15, CAST(N'2022-08-04T00:41:20.1880000' AS DateTime2), 200, 1, CAST(N'2022-08-04T00:41:20.1880000' AS DateTime2), 8, 14)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (17, CAST(N'2022-08-05T13:17:41.7610000' AS DateTime2), 700, 1, CAST(N'2022-08-05T13:17:41.7610000' AS DateTime2), 10, 17)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (18, CAST(N'2022-08-05T13:17:41.7660000' AS DateTime2), 2000, 1, CAST(N'2022-08-05T13:17:41.7660000' AS DateTime2), 10, 19)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (19, CAST(N'2022-08-08T13:26:50.2960000' AS DateTime2), 2000, 1, CAST(N'2022-08-08T13:26:50.2960000' AS DateTime2), 11, 19)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (20, CAST(N'2022-08-08T13:26:50.3330000' AS DateTime2), 500, 1, CAST(N'2022-08-08T13:26:50.3330000' AS DateTime2), 11, 15)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (21, CAST(N'2022-08-09T14:50:59.4620000' AS DateTime2), 700, 1, CAST(N'2022-08-09T14:50:59.4620000' AS DateTime2), 12, 17)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (22, CAST(N'2022-08-09T14:50:59.4700000' AS DateTime2), 2000, 1, CAST(N'2022-08-09T14:50:59.4700000' AS DateTime2), 12, 19)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (23, CAST(N'2022-08-10T09:42:57.5850000' AS DateTime2), 361, 3, CAST(N'2022-08-10T09:42:57.5850000' AS DateTime2), 13, 15)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (24, CAST(N'2022-08-10T09:45:51.7150000' AS DateTime2), 701, 1, CAST(N'2022-08-10T09:45:51.7150000' AS DateTime2), 14, 16)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (28, CAST(N'2022-08-14T17:08:39.9990000' AS DateTime2), 122, 1, CAST(N'2022-08-14T17:08:39.9990000' AS DateTime2), 17, 14)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (29, CAST(N'2022-08-14T17:08:40.0050000' AS DateTime2), 361, 1, CAST(N'2022-08-14T17:08:40.0050000' AS DateTime2), 17, 15)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (32, CAST(N'2022-08-16T16:01:19.1700000' AS DateTime2), 122, 3, CAST(N'2022-08-16T16:01:19.1700000' AS DateTime2), 20, 14)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (33, CAST(N'2022-08-16T16:01:19.1770000' AS DateTime2), 101, 1, CAST(N'2022-08-16T16:01:19.1770000' AS DateTime2), 20, 13)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (36, CAST(N'2022-08-17T15:56:11.5810000' AS DateTime2), 2000, 1, CAST(N'2022-08-17T15:56:11.5810000' AS DateTime2), 26, 19)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (37, CAST(N'2022-08-17T15:56:11.5870000' AS DateTime2), 122, 1, CAST(N'2022-08-17T15:56:11.5870000' AS DateTime2), 26, 14)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (39, CAST(N'2022-08-18T09:21:32.3780000' AS DateTime2), 701, 2, CAST(N'2022-08-18T09:21:32.3780000' AS DateTime2), 28, 16)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (42, CAST(N'2022-08-18T16:23:09.3500000' AS DateTime2), 361, 1, CAST(N'2022-08-18T16:23:09.3500000' AS DateTime2), 31, 15)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (44, CAST(N'2022-10-14T11:42:25.9440000' AS DateTime2), 2000, 1, CAST(N'2022-10-14T11:42:25.9440000' AS DateTime2), 33, 19)
INSERT [dbo].[order_detail] ([id], [create_at], [price], [quantity], [update_at], [order_id], [product_id]) VALUES (45, CAST(N'2022-11-16T21:19:07.9860000' AS DateTime2), 2000, 2, CAST(N'2022-11-16T21:19:07.9860000' AS DateTime2), 34, 19)
SET IDENTITY_INSERT [dbo].[order_detail] OFF
GO
SET IDENTITY_INSERT [dbo].[orders] ON 

INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (1, CAST(N'2022-08-02T15:03:25.6210000' AS DateTime2), 1, CAST(N'2022-08-02T15:03:25.6210000' AS DateTime2), 7, 11, 3)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (2, CAST(N'2022-08-03T10:22:28.7440000' AS DateTime2), 1, CAST(N'2022-08-03T10:22:28.7440000' AS DateTime2), 10, 12, 2)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (3, CAST(N'2022-08-03T17:06:23.8470000' AS DateTime2), 1, CAST(N'2022-08-03T17:06:23.8470000' AS DateTime2), 9, 17, 5)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (4, CAST(N'2022-08-03T17:07:55.2240000' AS DateTime2), 1, CAST(N'2022-08-03T17:07:55.2240000' AS DateTime2), 7, 18, 5)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (5, CAST(N'2022-08-03T17:08:39.0360000' AS DateTime2), 1, CAST(N'2022-08-03T17:08:39.0360000' AS DateTime2), 7, 19, 5)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (6, CAST(N'2022-08-03T17:33:41.3780000' AS DateTime2), 1, CAST(N'2022-08-03T17:33:41.3780000' AS DateTime2), 9, 20, 5)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (7, CAST(N'2022-08-03T17:34:20.6150000' AS DateTime2), 1, CAST(N'2022-08-03T17:34:20.6150000' AS DateTime2), 7, 21, 5)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (8, CAST(N'2022-08-04T00:41:20.1540000' AS DateTime2), 1, CAST(N'2022-08-04T00:41:20.1540000' AS DateTime2), 7, 22, 6)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (10, CAST(N'2022-08-05T13:17:41.7370000' AS DateTime2), 1, CAST(N'2022-08-05T13:17:41.7370000' AS DateTime2), 9, 24, 4)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (11, CAST(N'2022-08-08T13:26:50.2670000' AS DateTime2), 1, CAST(N'2022-08-08T13:26:50.2670000' AS DateTime2), 7, 25, 30)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (12, CAST(N'2022-08-09T14:50:59.4390000' AS DateTime2), 1, CAST(N'2022-08-09T14:50:59.4390000' AS DateTime2), 7, 27, 31)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (13, CAST(N'2022-08-10T09:42:57.5640000' AS DateTime2), 1, CAST(N'2022-08-10T09:42:57.5640000' AS DateTime2), 9, 28, 30)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (14, CAST(N'2022-08-10T09:45:51.7020000' AS DateTime2), 1, CAST(N'2022-08-10T09:45:51.7020000' AS DateTime2), 7, 29, 30)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (17, CAST(N'2022-08-14T17:08:39.9850000' AS DateTime2), 1, CAST(N'2022-08-14T17:08:39.9850000' AS DateTime2), 7, 32, 30)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (20, CAST(N'2022-08-16T16:01:19.1530000' AS DateTime2), 1, CAST(N'2022-08-16T16:01:19.1530000' AS DateTime2), 7, 35, 30)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (26, CAST(N'2022-08-17T15:56:11.5650000' AS DateTime2), 1, CAST(N'2022-08-17T15:56:11.5650000' AS DateTime2), 10, 42, 30)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (28, CAST(N'2022-08-18T09:21:32.3540000' AS DateTime2), 1, CAST(N'2022-08-18T09:21:32.3540000' AS DateTime2), 10, 44, 30)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (31, CAST(N'2022-08-18T16:23:09.3160000' AS DateTime2), 1, CAST(N'2022-08-18T16:23:09.3160000' AS DateTime2), 14, 47, 66)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (33, CAST(N'2022-10-14T11:42:25.9120000' AS DateTime2), 1, CAST(N'2022-10-14T11:42:25.9120000' AS DateTime2), 14, 49, 30)
INSERT [dbo].[orders] ([order_id], [create_at], [order_status], [update_at], [coupon_id], [shipping_id], [user_id]) VALUES (34, CAST(N'2022-11-16T21:19:07.9460000' AS DateTime2), 1, CAST(N'2022-11-16T21:19:07.9460000' AS DateTime2), 7, 50, 30)
SET IDENTITY_INSERT [dbo].[orders] OFF
GO
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([id], [content], [create_at], [image_path], [name], [price], [update_at], [category_id]) VALUES (13, N'Kid''s Bikes Liv 3 red version 9 improve performent							
							
							
							
							
							', CAST(N'2022-07-26T11:06:46.0820000' AS DateTime2), N'ROYRB12G4-3015-850-2-600x600.jpg', N'Kid''s Bikes Liv 3 red1', 101, CAST(N'2022-10-14T11:43:58.7900000' AS DateTime2), 8)
INSERT [dbo].[product] ([id], [content], [create_at], [image_path], [name], [price], [update_at], [category_id]) VALUES (14, N'Road Bike F2 Liv version 4 has fast run and improve performent 							
							', CAST(N'2022-07-26T11:13:12.1580000' AS DateTime2), N'detailRoadBike.jpg', N'Road Bike F2 Liv', 122, CAST(N'2022-08-16T13:59:12.3000000' AS DateTime2), 6)
INSERT [dbo].[product] ([id], [content], [create_at], [image_path], [name], [price], [update_at], [category_id]) VALUES (15, N'Road Bikes Tetrans 1 Red has fast run, active flexible and save healthy						
							', CAST(N'2022-07-26T11:15:07.9270000' AS DateTime2), N'atx-620-red-850.jpg', N'Road Bikes Tetrans 1 Red', 361, CAST(N'2022-08-16T13:59:07.0860000' AS DateTime2), 6)
INSERT [dbo].[product] ([id], [content], [create_at], [image_path], [name], [price], [update_at], [category_id]) VALUES (16, N'Mountain Bikes F1 PhiLip is beautiful bikes and cheap and flexible active when that is runing						
							
							
							
							
							', CAST(N'2022-07-26T11:19:48.2170000' AS DateTime2), N'3-9.jpg', N'Mountain Bikes F1 PhiLip', 701, CAST(N'2022-08-16T13:58:51.7140000' AS DateTime2), 7)
INSERT [dbo].[product] ([id], [content], [create_at], [image_path], [name], [price], [update_at], [category_id]) VALUES (17, N'Kid''s K1 PhiLip Black is good performment and flexible active when it is runing						
							
							', CAST(N'2022-07-26T11:32:37.4950000' AS DateTime2), N'adore-fw-16-2022-ice-green.jpg', N'Kid''s K1 PhiLip Black', 700, CAST(N'2022-08-16T13:51:47.1920000' AS DateTime2), 15)
INSERT [dbo].[product] ([id], [content], [create_at], [image_path], [name], [price], [update_at], [category_id]) VALUES (19, N'composite with female and it is beautiful and flexible runing						
							
							
							
							', CAST(N'2022-07-26T11:41:25.4850000' AS DateTime2), N'latte-26.jpg', N'Mountain Bikes TeTrans 1', 2000, CAST(N'2022-08-16T13:58:41.4300000' AS DateTime2), 7)
INSERT [dbo].[product] ([id], [content], [create_at], [image_path], [name], [price], [update_at], [category_id]) VALUES (20, N'The Splice strikes that perfect blend of bike path  
							
							
							', CAST(N'2022-08-17T15:20:03.3070000' AS DateTime2), N'Road-GIANT.jpg', N'LIVE URBAN Nexus 7 Speed Step Through Frame', 151, CAST(N'2022-08-17T15:20:48.4820000' AS DateTime2), 19)
SET IDENTITY_INSERT [dbo].[product] OFF
GO
SET IDENTITY_INSERT [dbo].[product_image] ON 

INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (75, NULL, N'ROYRB12G4-3015-800-3.jpg', 13)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (76, NULL, N'ROYRB12G4-3015-800-4.jpg', 13)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (77, NULL, N'ROYRB12G4-3015-800-6.jpg', 13)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (78, NULL, N'detail.jpg', 14)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (79, NULL, N'detail1.jpg', 14)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (80, NULL, N'detail2.jpg', 14)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (81, NULL, N'ATX-620-red-1.jpg', 15)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (82, NULL, N'ATX-620-red-3.jpg', 15)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (83, NULL, N'atx-620-red-main-3.jpg', 15)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (84, NULL, N'8-8 (1).jpg', 16)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (85, NULL, N'8-8.jpg', 16)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (86, NULL, N'9-9.jpg', 16)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (87, NULL, N'1-6.jpg', 17)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (88, NULL, N'4-5.jpg', 17)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (89, NULL, N'6-5.jpg', 17)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (90, NULL, N'7-5.jpg', 17)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (94, NULL, N'5.jpg', 19)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (95, NULL, N'7.jpg', 19)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (96, NULL, N'15.jpg', 19)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (100, NULL, N'propel-advanced-2-2021-850-6.jpg', 20)
INSERT [dbo].[product_image] ([id], [name], [path], [product_id]) VALUES (101, NULL, N'propel-advanced-2-2021-850-7.jpg', 20)
SET IDENTITY_INSERT [dbo].[product_image] OFF
GO
SET IDENTITY_INSERT [dbo].[revenues] ON 

INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (3, N'04-08-2022', 135, 270, 1)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (4, N'03-08-2022', 5535, 11070, 6)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (5, N'08-08-2022', 1125, 2250, 1)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (6, N'09-08-2022', 1215, 2430, 1)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (7, N'10-08-2022', 3340, 6681, 3)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (8, N'14-08-2022', 397, 795, 2)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (9, N'17-08-2022', 973, 1949, 3)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (10, N'16-08-2022', 1210, 2420, 2)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (11, N'02-08-2022', 1215, 2430, 1)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (12, N'05-08-2022', 1147, 2295, 1)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (13, N'18-08-2022', 740, 1482, 2)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (14, N'14-10-2022', 1000, 2000, 1)
INSERT [dbo].[revenues] ([id], [order_date], [profit], [sales], [total_order]) VALUES (15, N'16-11-2022', 1800, 3600, 1)
SET IDENTITY_INSERT [dbo].[revenues] OFF
GO
SET IDENTITY_INSERT [dbo].[shipping] ON 

INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (1, N'Can Tho', N'Alexx', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (2, N'Can Tho ', N'DenNy', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (3, N'Can Tho', N'Mai Xuan', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (4, N'Can Tho', N'Mai Xuan', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (5, N'Can Tho', N'Mai Xuan', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (6, N'Can Tho', N'Mai Xuan', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (7, N'Can Tho', N'Mai Xuan', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (8, N'Can Tho', N'Mai Xuan', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (9, N'Can Tho', N'Mai Xuan', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (10, N'Can Tho', N'Huy Nguyen', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (11, N'Can Tho', N'Mai Xuan', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (12, N'Vinh Long', N'Tuong', N'Announce before 23hours', N'0823338128')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (13, N'Ca Mau', N'Trong', N'Announce before 5 hours when delivery', N'0917265262')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (14, N'Ca Mau', N'Trong', N'Announce before 5 hours when delivery', N'0917265262')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (15, N'Ca Mau', N'Trong', N'Announce before 5 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (16, N'Ca Mau', N'Trong', N'Announce before 5 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (17, N'Ca Mau', N'Trong', N'Announce before 5 hours when delivery', N'0917256522')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (18, N'Ca MauCM', N'TrongCM', N'Announce before 4 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (19, N'Ca MauCM1', N'TrongCM1', N'Announce before 42 hours when delivery', N'0123585625')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (20, N'Kien Giang', N'Su', N'Announce before 1 hours when delivery', N'0945658963')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (21, N'An Giang', N'Thuc', N'Announce before 7 hours when delivery', N'0123520369')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (22, N'Jpan', N'Jhon', N'Announce before 1 hours', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (23, N'Korean', N'KenNy', N'Announce before 2 hours', N'0825556666')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (24, N'Can Tho', N'Jonhhhh', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (25, N'american', N'nJonh', N'announce before 2 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (26, N'Can Tho', N'Mai Xuan', N'', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (27, N'Can Tho', N'Phuc', N'Announce before 8 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (28, N'Can Tho ', N'Jonh 10-08-2022', N'Announce before 2 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (29, N'Can Tho 10-08', N'Jhon 10-08', N'Announce before 8 hours', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (30, N'Hau Giang', N'Jonh5%', N'Announce before 8 hours ', N'0823338127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (31, N'Can Tho', N'Trong Nguyen 14.08', N'announce before  3 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (32, N'Ca Mau', N'trong Purchase not counpount', N'announce before  3 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (33, N'Ca Mau', N'Pham Khanh Tuong', N'announce before  3 hours when delivery', N'082345698')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (34, N'Thoi Binh Ca Mau', N'Tony Nguyen', N'', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (35, N'3thang 2,TP Can Tho', N'CT18102_CanTho Company Brand', N'announce before 2 hours when delivery', N'0828878807')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (36, N'An Giang', N'Quy Purchase 17/8', N'announce before  3 hours when delivery', N'0917265262')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (37, N'An Giang', N'Quy Purchase 17/8', N'announce before  3 hours when delivery', N'0917265262')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (38, N'Ca Mau', N'Nguyen Trong Gmail', N'announce before  3 hours when delivery', N'08323235135')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (39, N'Ca Mau', N'Nguyen Trong Gmail', N'announce before  3 hours when delivery', N'08323235135')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (40, N'Vinh Log', N'trong Purchase', N'announce before 2 hours when delivery', N'089956816941')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (41, N'Ca Mau 56/43', N'Trong 17/08', N'announce before  3 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (42, N'Can Tho', N'Trong Nguyen', N'announce before  3 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (43, N'Vinh Log122', N'Trong Nguyen', N'announce before  3 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (44, N'american', N'jonh57', N'announce before  3 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (45, N'Can Tho', N'Jem', N'announce before  3 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (46, N'Can Tho', N'Trong Nguyen', N'announce before 2 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (47, N'Can Tho', N'Trong Nguyen', N'announce before  3 hours when delivery', N'0823438127')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (48, N'Can Tho', N'Pham Khanh Tuong', N'announce before 5 hours when delivery', N'0823437896')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (49, N'Can Tho', N'Trong Nguyen 14/10', N'announce before 5 hours when delivery', N'0917265265')
INSERT [dbo].[shipping] ([shipping_id], [shipping_address], [shipping_name], [shipping_note], [shipping_phone]) VALUES (50, N'Nguy?n T?t thành,TP Cà Mau', N'Nguyen Quoc Trong', N'11/06', N'0823438127')
SET IDENTITY_INSERT [dbo].[shipping] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_roles] ON 

INSERT [dbo].[tbl_roles] ([id], [name]) VALUES (1, N'ROLE_USER')
INSERT [dbo].[tbl_roles] ([id], [name]) VALUES (2, N'ROLE_ADMIN')
INSERT [dbo].[tbl_roles] ([id], [name]) VALUES (3, N'ROLE_PM')
SET IDENTITY_INSERT [dbo].[tbl_roles] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_users] ON 

INSERT [dbo].[tbl_users] ([id], [email], [passwork], [username]) VALUES (2, N'Tuong@gmail.com', N'$2a$10$gRaoSpy5qd2HSxhWlDQlrex7MozBLVY4Qli2pOQOFUbcNhrsSTGha', N'TuongPK')
INSERT [dbo].[tbl_users] ([id], [email], [passwork], [username]) VALUES (3, N'Phuong@gmail.com', N'$2a$10$SP2X9joM7O.t2QMsCR/09OT1DIJH34xF2Dwb7dkgHAyIKGD4fJwQO', N'Phuong')
INSERT [dbo].[tbl_users] ([id], [email], [passwork], [username]) VALUES (4, N'admin@gmail.com', N'$2a$10$0C2Z5mPPuRAs.lw0uJfcmeEgT0XraxqtlMO432B28A/04YEIEQrvy', N'admin123')
INSERT [dbo].[tbl_users] ([id], [email], [passwork], [username]) VALUES (5, N'trongb1809534@gmail.com', N'$2a$10$sPiGeBrfFRaG5lEo2UgVyOAVHrHx3GVsjmEUC9rNAPRR6cXHGTE5S', N'Trong')
INSERT [dbo].[tbl_users] ([id], [email], [passwork], [username]) VALUES (6, N'b1809534@gmail.com', N'$2a$10$6KuqFwcPtWipIuW4Pe1XleIGWRgilqQsbmXUtnFYUPWiUgTU7PxyC', N'trongb1809534')
INSERT [dbo].[tbl_users] ([id], [email], [passwork], [username]) VALUES (30, N'njonh5750@gmail.com', N'$2a$10$bmDjrVoE41ZLM6veFxubp.esnZVnsLRJpjSsvY676y0cwY98OUHwq', N'Nguyengmail')
INSERT [dbo].[tbl_users] ([id], [email], [passwork], [username]) VALUES (31, N'Phucb1609837@gmail.com', N'$2a$10$Z6TZsbfJKJs3ulsXKUSe6OW36ZM1onCbA0b8bvz/cY9sC1GZ0tRIu', N'Phuc')
INSERT [dbo].[tbl_users] ([id], [email], [passwork], [username]) VALUES (66, N'testmyky8@gmail.com', N'$2a$10$rq7acmz0.p3dm7OwplutleoTKdWdKtyiSjFR1wVtVdekZ8s/9SsQi', N'Testgmail')
SET IDENTITY_INSERT [dbo].[tbl_users] OFF
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (2, 1)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (3, 1)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (4, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (5, 1)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (6, 1)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (30, 1)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (31, 1)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (66, 1)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_hnrfbk2o64elqc8i5bnbjk596]    Script Date: 12/29/2022 11:58:12 AM ******/
ALTER TABLE [dbo].[revenues] ADD  CONSTRAINT [UK_hnrfbk2o64elqc8i5bnbjk596] UNIQUE NONCLUSTERED 
(
	[order_date] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UKc190nfu2w5xwvexf9dv08grsq]    Script Date: 12/29/2022 11:58:12 AM ******/
ALTER TABLE [dbo].[tbl_users] ADD  CONSTRAINT [UKc190nfu2w5xwvexf9dv08grsq] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UKj562wwmipqt96rkoqbo0jc34]    Script Date: 12/29/2022 11:58:12 AM ******/
ALTER TABLE [dbo].[tbl_users] ADD  CONSTRAINT [UKj562wwmipqt96rkoqbo0jc34] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FKb8bg2bkty0oksa3wiq5mp5qnc] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FKb8bg2bkty0oksa3wiq5mp5qnc]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FKrws2q0si6oyd6il8gqe2aennc] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FKrws2q0si6oyd6il8gqe2aennc]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK6h0o7brwb51gcrun3j8vic9ec] FOREIGN KEY([user_id])
REFERENCES [dbo].[tbl_users] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK6h0o7brwb51gcrun3j8vic9ec]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FKsemahq4easllj6if07wtarony] FOREIGN KEY([shipping_id])
REFERENCES [dbo].[shipping] ([shipping_id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FKsemahq4easllj6if07wtarony]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FKt07bh281b9osckd72c9hoismv] FOREIGN KEY([coupon_id])
REFERENCES [dbo].[counpon] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FKt07bh281b9osckd72c9hoismv]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK1mtsbur82frn64de7balymq9s] FOREIGN KEY([category_id])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK1mtsbur82frn64de7balymq9s]
GO
ALTER TABLE [dbo].[product_image]  WITH CHECK ADD  CONSTRAINT [FK6oo0cvcdtb6qmwsga468uuukk] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product_image] CHECK CONSTRAINT [FK6oo0cvcdtb6qmwsga468uuukk]
GO
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FKa8umiu84bxj8q8aqcvarqcay0] FOREIGN KEY([user_id])
REFERENCES [dbo].[tbl_users] ([id])
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FKa8umiu84bxj8q8aqcvarqcay0]
GO
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FKmpq7tdai5r24tf2vw0tfpo37m] FOREIGN KEY([role_id])
REFERENCES [dbo].[tbl_roles] ([id])
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FKmpq7tdai5r24tf2vw0tfpo37m]
GO
USE [master]
GO
ALTER DATABASE [MockApi] SET  READ_WRITE 
GO
