using System;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace E_mart.Migrations
{
    /// <inheritdoc />
    public partial class InitialCreate : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterDatabase()
                .Annotation("MySql:CharSet", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "categories",
                columns: table => new
                {
                    categoryid = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    category_description = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    categoryimage = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    categoryname = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: false, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    created_at = table.Column<DateTime>(type: "datetime(6)", maxLength: 6, nullable: true),
                    updated_at = table.Column<DateTime>(type: "datetime(6)", maxLength: 6, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.categoryid);
                })
                .Annotation("MySql:CharSet", "utf8mb4")
                .Annotation("Relational:Collation", "utf8mb4_0900_ai_ci");

            migrationBuilder.CreateTable(
                name: "users",
                columns: table => new
                {
                    userid = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    created_at = table.Column<DateTime>(type: "datetime(6)", maxLength: 6, nullable: true),
                    email = table.Column<string>(type: "varchar(255)", nullable: false, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    is_loyalty = table.Column<ulong>(type: "bit(1)", nullable: false),
                    password_hash = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: false, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    phonenumber = table.Column<string>(type: "varchar(15)", maxLength: 15, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    supercoin = table.Column<int>(type: "int", nullable: false),
                    updated_at = table.Column<DateTime>(type: "datetime(6)", maxLength: 6, nullable: true),
                    username = table.Column<string>(type: "varchar(50)", maxLength: 50, nullable: false, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4")
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.userid);
                })
                .Annotation("MySql:CharSet", "utf8mb4")
                .Annotation("Relational:Collation", "utf8mb4_0900_ai_ci");

            migrationBuilder.CreateTable(
                name: "subcategories",
                columns: table => new
                {
                    subcategoryid = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    subcategoryimage = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    subcategoryname = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: false, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    categoryid = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.subcategoryid);
                    table.ForeignKey(
                        name: "FK5cs3pf1w7kktveo6g9o25ita6",
                        column: x => x.categoryid,
                        principalTable: "categories",
                        principalColumn: "categoryid");
                })
                .Annotation("MySql:CharSet", "utf8mb4")
                .Annotation("Relational:Collation", "utf8mb4_0900_ai_ci");

            migrationBuilder.CreateTable(
                name: "products",
                columns: table => new
                {
                    productid = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    brand = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    created_at = table.Column<DateTime>(type: "datetime(6)", maxLength: 6, nullable: true),
                    description = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    description_en = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    description_fr = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    discount = table.Column<double>(type: "double", nullable: false),
                    image = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    isdeal = table.Column<ulong>(type: "bit(1)", nullable: false),
                    price = table.Column<double>(type: "double", nullable: false),
                    productname = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: false, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    productname_en = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    productname_fr = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    rating = table.Column<double>(type: "double", nullable: false),
                    stocks = table.Column<int>(type: "int", nullable: false),
                    updated_at = table.Column<DateTime>(type: "datetime(6)", maxLength: 6, nullable: true),
                    categoryid = table.Column<int>(type: "int", nullable: false),
                    subcategoryid = table.Column<int>(type: "int", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.productid);
                    table.ForeignKey(
                        name: "FK1krrsjgcawsfg8k8u4hm5gi8q",
                        column: x => x.categoryid,
                        principalTable: "categories",
                        principalColumn: "categoryid");
                    table.ForeignKey(
                        name: "FKi5prgvmxtqxb5fji9wc2qivr2",
                        column: x => x.subcategoryid,
                        principalTable: "subcategories",
                        principalColumn: "subcategoryid");
                })
                .Annotation("MySql:CharSet", "utf8mb4")
                .Annotation("Relational:Collation", "utf8mb4_0900_ai_ci");

            migrationBuilder.CreateTable(
                name: "carts",
                columns: table => new
                {
                    cartid = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    created_at = table.Column<DateTime>(type: "datetime(6)", maxLength: 6, nullable: true),
                    quantity = table.Column<int>(type: "int", nullable: false),
                    updated_at = table.Column<DateTime>(type: "datetime(6)", maxLength: 6, nullable: true),
                    productid = table.Column<int>(type: "int", nullable: false),
                    userid = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.cartid);
                    table.ForeignKey(
                        name: "FK7omdju5h5l95oek80ymvb56uq",
                        column: x => x.userid,
                        principalTable: "users",
                        principalColumn: "userid");
                    table.ForeignKey(
                        name: "FKf02ibgv71d0qx173h41rsjeri",
                        column: x => x.productid,
                        principalTable: "products",
                        principalColumn: "productid");
                })
                .Annotation("MySql:CharSet", "utf8mb4")
                .Annotation("Relational:Collation", "utf8mb4_0900_ai_ci");

            migrationBuilder.CreateTable(
                name: "cartitems",
                columns: table => new
                {
                    cartitemid = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    added_at = table.Column<DateTime>(type: "datetime(6)", maxLength: 6, nullable: true),
                    quantity = table.Column<int>(type: "int", nullable: false),
                    cartid = table.Column<int>(type: "int", nullable: false),
                    productid = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.cartitemid);
                    table.ForeignKey(
                        name: "FK5vi0udj5ia7o0h6k6r0op1do6",
                        column: x => x.cartid,
                        principalTable: "carts",
                        principalColumn: "cartid");
                    table.ForeignKey(
                        name: "FKp74gufw7pdc246iolcggngpp",
                        column: x => x.productid,
                        principalTable: "products",
                        principalColumn: "productid");
                })
                .Annotation("MySql:CharSet", "utf8mb4")
                .Annotation("Relational:Collation", "utf8mb4_0900_ai_ci");

            migrationBuilder.CreateTable(
                name: "orders",
                columns: table => new
                {
                    orderid = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    amount = table.Column<double>(type: "double", nullable: false),
                    order_date = table.Column<DateTime>(type: "datetime(6)", maxLength: 6, nullable: true),
                    payment_method = table.Column<string>(type: "enum('COD','CREDIT_CARD','PAYPAL')", nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    status = table.Column<string>(type: "enum('CANCELLED','DELIVERED','PENDING','PROCESSING','SHIPPED')", nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    cartid = table.Column<int>(type: "int", nullable: false),
                    userid = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.orderid);
                    table.ForeignKey(
                        name: "FKhwdcdaiq23cketwygk74e29c4",
                        column: x => x.cartid,
                        principalTable: "carts",
                        principalColumn: "cartid");
                    table.ForeignKey(
                        name: "FKpnm1eeupqm4tykds7k3okqegv",
                        column: x => x.userid,
                        principalTable: "users",
                        principalColumn: "userid");
                })
                .Annotation("MySql:CharSet", "utf8mb4")
                .Annotation("Relational:Collation", "utf8mb4_0900_ai_ci");

            migrationBuilder.CreateTable(
                name: "payment_details",
                columns: table => new
                {
                    paymentid = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    payment_method = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    payment_status = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: true, collation: "utf8mb4_0900_ai_ci")
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    orderid = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.paymentid);
                    table.ForeignKey(
                        name: "FKdfd34sf5c5xu3ralj3r11u3ih",
                        column: x => x.orderid,
                        principalTable: "orders",
                        principalColumn: "orderid");
                })
                .Annotation("MySql:CharSet", "utf8mb4")
                .Annotation("Relational:Collation", "utf8mb4_0900_ai_ci");

            migrationBuilder.CreateIndex(
                name: "FK5vi0udj5ia7o0h6k6r0op1do6",
                table: "cartitems",
                column: "cartid");

            migrationBuilder.CreateIndex(
                name: "FKp74gufw7pdc246iolcggngpp",
                table: "cartitems",
                column: "productid");

            migrationBuilder.CreateIndex(
                name: "FK7omdju5h5l95oek80ymvb56uq",
                table: "carts",
                column: "userid");

            migrationBuilder.CreateIndex(
                name: "FKf02ibgv71d0qx173h41rsjeri",
                table: "carts",
                column: "productid");

            migrationBuilder.CreateIndex(
                name: "FKhwdcdaiq23cketwygk74e29c4",
                table: "orders",
                column: "cartid");

            migrationBuilder.CreateIndex(
                name: "FKpnm1eeupqm4tykds7k3okqegv",
                table: "orders",
                column: "userid");

            migrationBuilder.CreateIndex(
                name: "FKdfd34sf5c5xu3ralj3r11u3ih",
                table: "payment_details",
                column: "orderid");

            migrationBuilder.CreateIndex(
                name: "FK1krrsjgcawsfg8k8u4hm5gi8q",
                table: "products",
                column: "categoryid");

            migrationBuilder.CreateIndex(
                name: "FKi5prgvmxtqxb5fji9wc2qivr2",
                table: "products",
                column: "subcategoryid");

            migrationBuilder.CreateIndex(
                name: "FK5cs3pf1w7kktveo6g9o25ita6",
                table: "subcategories",
                column: "categoryid");

            migrationBuilder.CreateIndex(
                name: "UK6dotkott2kjsp8vw4d0m25fb7",
                table: "users",
                column: "email",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "UK70jmct5ej765l57mlcrdhxn1c",
                table: "users",
                column: "phonenumber",
                unique: true);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "cartitems");

            migrationBuilder.DropTable(
                name: "payment_details");

            migrationBuilder.DropTable(
                name: "orders");

            migrationBuilder.DropTable(
                name: "carts");

            migrationBuilder.DropTable(
                name: "users");

            migrationBuilder.DropTable(
                name: "products");

            migrationBuilder.DropTable(
                name: "subcategories");

            migrationBuilder.DropTable(
                name: "categories");
        }
    }
}
